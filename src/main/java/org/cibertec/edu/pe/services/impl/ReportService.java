package org.cibertec.edu.pe.services.impl;

import net.sf.jasperreports.engine.JRException;
import org.cibertec.edu.pe.dto.FileReportDTO;
import org.cibertec.edu.pe.enums.TipoReporteEnum;
import org.cibertec.edu.pe.services.IReportService;
import org.cibertec.edu.pe.util.JasperReportManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Service
public class ReportService implements IReportService {
    @Autowired
    private JasperReportManager reportManager;
    @Autowired
    private DataSource dataSource;

    @Override
    public FileReportDTO getReportFile(String fileName, Map<String, Object> params, TipoReporteEnum tipo) throws JRException, IOException, SQLException {
        String extension = String.format(".%s", tipo.tipo);
        FileReportDTO dto = FileReportDTO.builder().build();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        dto.setFileName(String.format("%s_%s.%s", fileName, LocalDateTime.now().format(dtf), extension));
        dto.setFileExtension(extension);
        ByteArrayOutputStream stream = reportManager.exportFromConnection(fileName, tipo.tipo, params,
                dataSource.getConnection());
        byte[] bs = stream.toByteArray();
        dto.setStream(new ByteArrayInputStream(bs));
        dto.setLength(bs.length);
        return dto;
    }
}
