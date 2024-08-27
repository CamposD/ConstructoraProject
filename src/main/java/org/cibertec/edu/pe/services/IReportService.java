package org.cibertec.edu.pe.services;

import net.sf.jasperreports.engine.JRException;
import org.cibertec.edu.pe.dto.FileReportDTO;
import org.cibertec.edu.pe.enums.TipoReporteEnum;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

public interface IReportService {
  public FileReportDTO getReportFile(String fileName, Map<String, Object> params, TipoReporteEnum tipo)
    throws JRException, IOException, SQLException;
}
