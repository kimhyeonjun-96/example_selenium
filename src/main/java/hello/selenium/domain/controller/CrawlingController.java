package hello.selenium.domain.controller;

import hello.selenium.domain.dto.CrawlingDto;
import jakarta.servlet.http.HttpServletRequest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CrawlingController {

    @GetMapping("/crawling")
    public ResponseEntity<Integer> crawling(HttpServletRequest request) {

        List<CrawlingDto> list = new ArrayList<>();

        System.setProperty("webdriver.chrome.driver", "/Users/jjun/Desktop/projects/selenium_프로세스/selenium/chromedriver");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--headless");

        WebDriver driver = new ChromeDriver(options);
        try {

            // 네이버 전체 언론사 페이지
            driver.get("https://news.naver.com/main/officeList.naver"); //크롤링할 사이트의 url, 즉 해당 url로 이동한다.
            List<WebElement> group_list = driver.findElements(By.className("group_list"));

            for(WebElement element : group_list){
                System.out.println("***** type06_headline start *****");
                System.out.println();

                // 각 언론사 페이지 url
                    // 각 언론사 페이지 중 제일 마지막은 속보 뉴스 페이지로 기사 리스트 이미지, 제목을 가져와서 최근신문 페이지에 넣으면 될 것 같다!
                List<WebElement> url_list = element.findElements(By.tagName("a"));
                for (WebElement webElement : url_list) {
                    System.out.println(webElement.getAttribute("href"));
                }
                // 각 언론사 1면 기사 url을 통해서 언론사 리스트에 "오늘의 1면 기사"란에 1면 기사 제목 붙이기
                //

//                String data = element.getText();
//                WebElement imgs = element.findElement(By.tagName("img"));
//                String img = imgs.getAttribute("src");
//
                CrawlingDto dto = new CrawlingDto();
//                dto.setData(data);
//                dto.setImg(img);

//                System.out.println(dto.toString());
                System.out.println("===========================================");
//                list.add(dto);
            }
            return ResponseEntity.ok(1);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(0);
        }finally {
            driver.close();
        }
    }

}
