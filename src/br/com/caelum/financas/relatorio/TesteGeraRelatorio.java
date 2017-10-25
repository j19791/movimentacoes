package br.com.caelum.financas.relatorio;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import br.com.caelum.financas.ConnectionFactory;

public class TesteGeraRelatorio {

	public static void main(String[] args) throws SQLException, JRException,
			FileNotFoundException {

		JasperCompileManager.compileReportToFile("gastos_por_mes2.jrxml");

		JasperCompileManager
				.compileReportToFile("gastos_por_mes2_subreport1.jrxml");

		Connection conexao = new ConnectionFactory().getConnection();

		Map<String, Object> parametros = new HashMap<String, Object>();
		Connection connexao = new ConnectionFactory().getConnection();

		JasperPrint print = JasperFillManager.fillReport(
				"gastos_por_mes2.jasper", parametros, connexao);

		JRExporter exporter = new JRHtmlExporter();
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,
				new FileOutputStream("gastos_por_mes2.html"));
		exporter.exportReport();

		JRExporter exporter2 = new JRXlsExporter();
		exporter2.setParameter(JRExporterParameter.JASPER_PRINT, print);
		exporter2.setParameter(JRExporterParameter.OUTPUT_STREAM,
				new FileOutputStream("gastos_por_mes2.xls"));
		exporter2.exportReport();

		JRExporter exporter3 = new JRPdfExporter();
		exporter3.setParameter(JRExporterParameter.JASPER_PRINT, print);
		exporter3.setParameter(JRExporterParameter.OUTPUT_STREAM,
				new FileOutputStream("gastos_por_mes2.pdf"));
		exporter3.exportReport();

		conexao.close();
	}
}
