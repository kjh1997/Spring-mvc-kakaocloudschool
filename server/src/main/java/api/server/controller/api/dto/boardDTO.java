package api.server.controller.api.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class boardDTO {
    private String title;
    private String context;
    private LocalDateTime localDateTime;
    private String category_id;
    private String user_id;
}
