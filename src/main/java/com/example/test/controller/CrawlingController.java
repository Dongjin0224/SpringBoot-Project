package com.example.test.controller;


import com.example.test.model.appointment.vo.ReserveVO;
import com.example.test.services.AppointmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@Slf4j
public class CrawlingController {



    //WebDriver 설정
    private WebDriver driver;
    private WebElement element;
    private String url;

    //Properties 설정
    public static String WEB_DRIVER_ID = "webdriver.chrome.driver";
    public static String WEB_DRIVER_PATH = "/Users/kimdongjin/eclipse/chromedriver";
    public static String URL = "https://center-pf.kakao.com/_rxigIb/messages/new/feed";
    // (여기선 naver 를 사용해봤습니다.)

    public CrawlingController(){
        //System Property SetUp
        System.setProperty("webdriver.chrome.whitelistedIps", "");
        System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
        //Driver SetUp
        ChromeOptions options = new ChromeOptions();
        options.setCapability("ignoreProtectedModeSettings", true);
        options.addArguments("headless");
        driver = new ChromeDriver(options);
    }

    public void open(String[] name, String[] phoneNum){

        try {
            log.info("크롤링 시작");

            driver.get(URL);
            log.info("사이트 들어감");

            element = driver.findElement(By.id("id_email_2"));
            Thread.sleep(1000);
            element.sendKeys("ljh3745043@naver.com");
            log.info("아이디 입력 완료");

            element = driver.findElement(By.id("id_password_3"));
            Thread.sleep(1000);
            element.sendKeys("jh5043");
            log.info("비밀번호 입력 완료");
            Thread.sleep(1000);

            element = driver.findElement(By.className("submit"));
            element.click();
            log.info("로그인 버튼 클릭 완료");
            Thread.sleep(1000);

            WebElement parent4 = driver.findElement(By.className("wrap_inp"));
            List<WebElement> button4 = parent4.findElements(By.className("lab_g"));
            Thread.sleep(1000);
            button4.get(1).click();
            log.info("정보성 클릭 완료");
            Thread.sleep(1000);

            String msg ="\n" + name[0] + "님의 예약이 접수되었습니다.";   /* 보낼 문자 내용 설정 */
            element = driver.findElement(By.cssSelector("textarea[name='messageWrite']"));
            Thread.sleep(1000);
            element.sendKeys(msg);
            log.info("메세지 내용 입력 완료");

            Thread.sleep(1000);
            element = driver.findElement(By.className("btn_g2"));
            element.click();
            log.info("다음버튼 클릭 완료");
            Thread.sleep(1000);

            WebElement parent = driver.findElement(By.className("wrap_btn"));
            List<WebElement> button = parent.findElements(By.className("btn_g"));
            button.get(2).click();
            log.info("테스트 발송 버튼 클릭 완료");
            Thread.sleep(1000);

            WebElement input = null;
            input = driver.findElement(By.id("phoneNumber"));
            input.sendKeys(phoneNum[0]);   /* 진료 예약한 회원 번호와, 의사 번호 입력 */
            Thread.sleep(1000);
            log.info("핸드폰번호 입력 완료");

            Thread.sleep(1000);
            WebElement parent2 = driver.findElement(By.className("certify_g"));
            List<WebElement> button2 = parent2.findElements(By.className("btn_g2"));
            button2.get(0).click();
            log.info("전송 완료");
            Thread.sleep(1000);

            WebElement parent3 = driver.findElement(By.className("layer_alert"));
            List<WebElement> button3 = parent3.findElements(By.className("btn_g2"));
            button3.get(0).click();
            log.info("확인 완료");
            Thread.sleep(1000);

            /* 의사한테 문자 보내기*/
            parent = driver.findElement(By.className("wrap_btn"));
            button = parent.findElements(By.className("btn_g"));
            button.get(0).click();
            log.info("이전 버튼 클릭 완료");
            Thread.sleep(1000);

            String msg2 ="\n" + name[1] + " 전문의님 확인바랍니다.";   /* 보낼 문자 내용 설정 */
            element = driver.findElement(By.cssSelector("textarea[name='messageWrite']"));
            Thread.sleep(1000);
            element.sendKeys(msg2);
            log.info("메세지 내용 입력 완료");

            Thread.sleep(1000);
            element = driver.findElement(By.className("btn_g2"));
            element.click();
            log.info("다음버튼 클릭 완료");
            Thread.sleep(1000);

            parent = driver.findElement(By.className("wrap_btn"));
            button = parent.findElements(By.className("btn_g"));
            button.get(2).click();
            log.info("테스트 발송 버튼 클릭 완료");
            Thread.sleep(1000);

            input = null;
            input = driver.findElement(By.id("phoneNumber"));
            input.sendKeys(phoneNum[1]);  /* 진료 예약한 회원 번호와, 의사 번호 입력 */
            Thread.sleep(1000);
            log.info("핸드폰번호 입력 완료");

            Thread.sleep(1000);
            parent2 = driver.findElement(By.className("certify_g"));
            button2 = parent2.findElements(By.className("btn_g2"));
            button2.get(0).click();
            log.info("전송 완료");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            driver.close();
            driver.quit();
        }
    }

}