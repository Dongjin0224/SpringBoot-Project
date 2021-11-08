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

    /*
        웹 크롤링 라이브러리
        1. Jsoup
	        정적 데이터를 비교적 빠르게 수집할 수 있지만 브라우저가 아닌
	        HTTP Request를 사용하기 때문에 동적 데이터를 수집하기 위해서는
	        해당 서버의 인증키 요구 등 수집할 수 없는 경우가 많다.

        2. Selenium
	        Jsoup에 비해 느리지만 브라우저 드라이버를 사용하여 동적 데이터도 수집 가능하다.

        Selenium을 이용한 크롤링 - ebdriver라는 API를 통해 운영체제에 설치된 Chrome등의 브라우저를 제어
        1. Selenium Library 추가 - build 툴은 maven으로 pom.xml에 추가해준다. 이때, 크롬드라이버의 버전과 일치해야한다.
        https://www.seleniumhq.org/download/ 여기서 java버전을 받아 Build Pathed에 넣어주는 방식도 있다.
        2. 크롬 드라이버 다운 (https://chromedriver.chromium.org/downloads)
        3. 설정 및 세팅
      */

    //WebDriver 설정
    private WebDriver driver;
    private WebElement element;
    private String url;

    //Properties 설정
        //드라이버 설정
    public static String WEB_DRIVER_ID = "webdriver.chrome.driver";
        //드라이버 경로 설정
    public static String WEB_DRIVER_PATH = "C:/chromedriver.exe";
        //최초에 들어갈 URL 설정
    public static String URL = "https://center-pf.kakao.com/_rxigIb/messages/new/feed";

    public CrawlingController(){
        //System Property SetUp
        System.setProperty("webdriver.chrome.whitelistedIps", ""); //여기서 화이트 리스트란 특정 권한, 서비스, 이동, 접근, 인식에 대해 명시적으로 허가하는 목록을 말함.
        System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
        //Driver SetUp
        ChromeOptions options = new ChromeOptions();
        options.setCapability("ignoreProtectedModeSettings", true);
        options.addArguments("headless");
        //여러 드라이버 중 크롬드라이버에 옵션을 넣고 세팅
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
            Thread.sleep(2000);

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