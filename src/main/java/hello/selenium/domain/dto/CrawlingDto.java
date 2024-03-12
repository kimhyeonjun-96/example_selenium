package hello.selenium.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@ToString
public class CrawlingDto {

    private String url;
    private String newsName;
    private String data;
    private String img;

    private List<CrawlingDto> list;
}
