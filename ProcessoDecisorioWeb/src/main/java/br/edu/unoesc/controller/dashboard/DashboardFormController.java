package br.edu.unoesc.controller.dashboard;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.HorizontalBarChartModel;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.MeterGaugeChartModel;
import org.primefaces.model.chart.PieChartModel;

import br.edu.unoesc.api.InsumoService;
import br.edu.unoesc.api.PrecoSacaExteriorService;
import br.edu.unoesc.api.PrecoSacaNacionalService;
import br.edu.unoesc.api.SafraService;
import br.edu.unoesc.api.TipoCulturaService;
import br.edu.unoesc.common.Insumo;
import br.edu.unoesc.common.PrecoSacaExterior;
import br.edu.unoesc.common.PrecoSacaNacional;
import br.edu.unoesc.common.Safra;
import br.edu.unoesc.common.TipoCultura;

@ManagedBean
@ViewScoped
public class DashboardFormController implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private PrecoSacaExteriorService precoSacaExteriorService;

	@EJB
	private PrecoSacaNacionalService precoSacaNacionalService;

	@EJB
	private SafraService safraService;

	@EJB
	private TipoCulturaService tipoCulturaService;
	
	@EJB
	private InsumoService insumoService;

	private List<TipoCultura> listaTipoCultura;

	private LineChartModel precoSacaExterior;
	private LineChartModel precoSacaNacional;
	private PieChartModel historicoSafras;
	private MeterGaugeChartModel limiteDiasArmazenagem;
	private BarChartModel valoresInsumos;
	private HorizontalBarChartModel maisVantajoso;

	@PostConstruct
	public void init() {
		iniciarVariaveis();
		popularListas();
		criarGraficoPrecoSacaExterior();
		criarGraficoPrecoSacaNacional();
		criarGraficoHistoricoSafras();
		criarGraficoLimiteDiasArmazenagem();
		criarGraficoValoresInsumos();
		criarGraficaMaisVantajoso();
	}

	private void criarGraficaMaisVantajoso() {
		maisVantajoso = new HorizontalBarChartModel();
        
		for (TipoCultura cultura : listaTipoCultura) {
			ChartSeries valor = new ChartSeries();
			valor.setLabel(cultura.getDescricao());
			
			List<Insumo> todosPorCultura = insumoService.buscarTodosPorCultura(cultura.getId());
			
			PrecoSacaNacional ultimaCotacao = precoSacaNacionalService.buscarUltimaCotacaoPorCultura(cultura.getId());
			
			List<Safra> safras = safraService.buscarTodosPorCultura(cultura.getId());
			
			Double mediaHectares = safras.stream().mapToDouble(x -> x.getQtdHectares()).average().getAsDouble();
			Double mediaDeSacas = null;
			
			if (cultura.isMilho()) {
				mediaDeSacas = mediaHectares * 170;
			}

			if (cultura.isSoja()) {
				mediaDeSacas = mediaHectares * 70;
			}
			
			Double totalInsumosPorHectare = todosPorCultura.stream().mapToDouble(x -> x.getValor()).sum();
			Double totalInsumos = (mediaHectares * totalInsumosPorHectare);
			
			Double lucroFinalTeorico = (mediaDeSacas * ultimaCotacao.getValor()) - totalInsumos;
			
			valor.set("Milho/Soja", lucroFinalTeorico);
			
			maisVantajoso.addSeries(valor);
		}
 
		maisVantajoso.setTitle("O que esta valendo mais hoje?");
		maisVantajoso.setLegendPosition("e");
		maisVantajoso.setStacked(true);
 
        Axis xAxis = maisVantajoso.getAxis(AxisType.X);
        xAxis.setLabel("Lucro R$");
        xAxis.setMin(0);
        xAxis.setMax(1000000);
 
        Axis yAxis = maisVantajoso.getAxis(AxisType.Y);
        yAxis.setLabel("Culturas");
		
	}

	private void criarGraficoValoresInsumos() {
		valoresInsumos = iniciarValoresInsumos();
        
		valoresInsumos.setTitle("Valor Insumos p/ Hectare");
		valoresInsumos.setLegendPosition("ne");
 
        Axis xAxis = valoresInsumos.getAxis(AxisType.X);
        xAxis.setLabel("Cultura");
 
        Axis yAxis = valoresInsumos.getAxis(AxisType.Y);
        yAxis.setLabel("Total R$");
        yAxis.setMin(0);
        yAxis.setMax(4000);
	}
	
    private BarChartModel iniciarValoresInsumos() {
        BarChartModel valorInsumo = new BarChartModel();
        
		for (TipoCultura cultura : listaTipoCultura) {
			ChartSeries insumo = new ChartSeries();
			insumo.setLabel(cultura.getDescricao());
			List<Insumo> todosPorCultura = insumoService.buscarTodosPorCultura(cultura.getId());
			
			double soma = todosPorCultura.stream().mapToDouble(x -> x.getValor()).sum();
			
			insumo.set("Milho/Soja", soma);
			
			valorInsumo.addSeries(insumo);
		}
 
        return valorInsumo;
    }

	private void criarGraficoLimiteDiasArmazenagem() {

		Safra safraValida = safraService.buscarUltimaSafraValida();
		Date dataColheita = safraValida.getDataColheira();
		Date hoje = Date.from(Instant.now());
		Long dias = getDiasDeDiferenca(dataColheita, hoje);
		
		limiteDiasArmazenagem = iniciarLimite(dias);
		limiteDiasArmazenagem.setTitle("Dias Inicio Custo Galpão");
		limiteDiasArmazenagem.setSeriesColors("66cc66,93b75f,E7E658,cc6666");
		limiteDiasArmazenagem.setGaugeLabel(dias + " Dias");
		limiteDiasArmazenagem.setGaugeLabelPosition("bottom");

	}

	private MeterGaugeChartModel iniciarLimite(Long dias) {
		List<Number> intervals = new ArrayList<Number>() {
			{
				add(20);
				add(50);
				add(181);
				add(362);
			}
		};


		return new MeterGaugeChartModel(dias, intervals);
	}

	private long getDiasDeDiferenca(Date d1, Date d2) {
		long diff = d2.getTime() - d1.getTime();
		return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
	}

	private void criarGraficoHistoricoSafras() {
		historicoSafras = new PieChartModel();

		for (TipoCultura cultura : listaTipoCultura) {
			Double valor = null;
			List<Safra> safras = safraService.buscarTodosPorCultura(cultura.getId());

			if (cultura.isMilho()) {
				valor = safras.stream().mapToDouble(x -> x.getQtdHectares()).sum() * 170;
			}

			if (cultura.isSoja()) {
				valor = safras.stream().mapToDouble(x -> x.getQtdHectares()).sum() * 70;
			}

			historicoSafras.set(cultura.getDescricao(), valor);
		}

		historicoSafras.setTitle("Total Geral de Sacas Produzidas");
		historicoSafras.setLegendPosition("e");
		historicoSafras.setFill(false);
		historicoSafras.setShowDataLabels(true);
		historicoSafras.setDiameter(200);
		historicoSafras.setShadow(false);
	}

	private void criarGraficoPrecoSacaNacional() {
		precoSacaNacional = iniciarMontagemPrecoSacaNacional();
		precoSacaNacional.setTitle("Preço Saca Nacional 60kg");
		precoSacaNacional.setLegendPosition("e");
		precoSacaNacional.getAxes().put(AxisType.X, new CategoryAxis("Mês"));
		Axis yAxis = precoSacaNacional.getAxis(AxisType.Y);
		yAxis.setLabel("R$");
		yAxis.setMin(20);
		yAxis.setMax(100);
	}

	private LineChartModel iniciarMontagemPrecoSacaNacional() {
		LineChartModel model = new LineChartModel();

		for (TipoCultura cultura : listaTipoCultura) {
			ChartSeries linha = new ChartSeries();
			linha.setLabel(cultura.getDescricao());

			List<PrecoSacaNacional> precos = precoSacaNacionalService.buscarTodosPorTipoCultura(cultura.getId());

			for (PrecoSacaNacional preco : precos) {
				String mes = retornarNomeMes(preco.getMes());
				linha.set(mes, preco.getValor());
			}

			model.addSeries(linha);
		}

		return model;
	}

	private void criarGraficoPrecoSacaExterior() {
		precoSacaExterior = iniciarMontagemPrecoSacaExterior();
		precoSacaExterior.setTitle("Preço Saca Exterior 25kg");
		precoSacaExterior.setLegendPosition("e");
		precoSacaExterior.getAxes().put(AxisType.X, new CategoryAxis("Mês"));
		Axis yAxis = precoSacaExterior.getAxis(AxisType.Y);
		yAxis.setLabel("U$$");
		yAxis.setMin(2);
		yAxis.setMax(10);

	}

	private LineChartModel iniciarMontagemPrecoSacaExterior() {
		LineChartModel model = new LineChartModel();

		for (TipoCultura cultura : listaTipoCultura) {
			ChartSeries linha = new ChartSeries();
			linha.setLabel(cultura.getDescricao());

			List<PrecoSacaExterior> precos = precoSacaExteriorService.buscarTodosPorTipoCultura(cultura.getId());

			for (PrecoSacaExterior preco : precos) {
				String mes = retornarNomeMes(preco.getMes());
				linha.set(mes, preco.getValor());
			}

			model.addSeries(linha);
		}

		return model;
	}

	private void iniciarVariaveis() {
		listaTipoCultura = new ArrayList<>();
	}

	private void popularListas() {
		listaTipoCultura = tipoCulturaService.buscarTodos();
	}

	private String retornarNomeMes(Long mes) {
		switch (Integer.parseInt(mes.toString())) {
		case 1:
			return "Janeiro";
		case 2:
			return "Fevereiro";
		case 3:
			return "Março";
		case 4:
			return "Abril";
		case 5:
			return "Maio";
		case 6:
			return "Junho";
		case 7:
			return "Julho";
		case 8:
			return "Agosto";
		case 9:
			return "Setembro";
		case 10:
			return "Outubro";
		case 11:
			return "Novembro";
		case 12:
			return "Dezembro";
		default:
			return "";
		}
	}

	public LineChartModel getPrecoSacaExterior() {
		return precoSacaExterior;
	}

	public void setPrecoSacaExterior(LineChartModel precoSacaExterior) {
		this.precoSacaExterior = precoSacaExterior;
	}

	public LineChartModel getPrecoSacaNacional() {
		return precoSacaNacional;
	}

	public void setPrecoSacaNacional(LineChartModel precoSacaNacional) {
		this.precoSacaNacional = precoSacaNacional;
	}

	public PieChartModel getHistoricoSafras() {
		return historicoSafras;
	}

	public void setHistoricoSafras(PieChartModel historicoSafras) {
		this.historicoSafras = historicoSafras;
	}

	public List<TipoCultura> getListaTipoCultura() {
		return listaTipoCultura;
	}

	public void setListaTipoCultura(List<TipoCultura> listaTipoCultura) {
		this.listaTipoCultura = listaTipoCultura;
	}

	public MeterGaugeChartModel getLimiteDiasArmazenagem() {
		return limiteDiasArmazenagem;
	}

	public void setLimiteDiasArmazenagem(MeterGaugeChartModel limiteDiasArmazenagem) {
		this.limiteDiasArmazenagem = limiteDiasArmazenagem;
	}

	public BarChartModel getValoresInsumos() {
		return valoresInsumos;
	}

	public void setValoresInsumos(BarChartModel valoresInsumos) {
		this.valoresInsumos = valoresInsumos;
	}

	public HorizontalBarChartModel getMaisVantajoso() {
		return maisVantajoso;
	}

	public void setMaisVantajoso(HorizontalBarChartModel maisVantajoso) {
		this.maisVantajoso = maisVantajoso;
	}

}
