package hello.selenium.domain.service;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;

import java.util.List;


@Configuration
@EnableScheduling
public class NotificationSchedulingConfig implements SchedulingConfigurer {


    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {

        taskRegistrar.addTriggerTask(
                // 트리거가 될 수 있는 로직을 작성
                crawling(), triggerContext -> {
                    CronTrigger cronTrigger = new CronTrigger("0 * * * * ?"); // 매분마다 실행
                    return cronTrigger.nextExecutionTime(triggerContext).toInstant();
                }
        );
    }

    private Runnable crawling(){

        return () -> {
            System.setProperty("webdriver.chrome.driver", "/Users/jjun/Desktop/projects/selenium_프로세스/selenium/chromedriver");

            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            options.addArguments("--headless");

            WebDriver driver = new ChromeDriver(options);
            try {

                // 네이버 전체 언론사 페이지
                driver.get("https://news.naver.com/main/officeList.naver"); //크롤링할 사이트의 url, 즉 해당 url로 이동한다.
                List<WebElement> group_list = driver.findElements(By.className("group_list"));

                for (WebElement element : group_list) {
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

                    System.out.println("===========================================");
                }
            } catch (Exception e) {

            } finally {
                driver.quit();
            }
        };
    }
}
