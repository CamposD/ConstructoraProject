package org.cibertec.edu.pe.dto;

import lombok.Builder;
import lombok.Data;

import java.io.ByteArrayInputStream;

@Builder
@Data
public class FileReportDTO {
    private String fileName;
    private String fileExtension;
    private ByteArrayInputStream stream;
    private int length;
}
