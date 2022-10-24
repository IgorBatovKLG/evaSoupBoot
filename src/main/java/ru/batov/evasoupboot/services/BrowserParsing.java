package ru.batov.evasoupboot.services;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;
import ru.batov.evasoupboot.dao.DirectionDao;
import ru.batov.evasoupboot.domain.Direction;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

@Service
public class BrowserParsing {

    private final DateService dateService;

    private final DirectionDao directionDao;

    private final ConfigurableApplicationContext context;

    public BrowserParsing(DateService dateService, DirectionDao directionDao, ConfigurableApplicationContext context) {
        this.dateService = dateService;
        this.directionDao = directionDao;
        this.context = context;
    }


    public void parsingDirection(LocalDate startDate, LocalDate endDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        GetCookies cookies = new GetCookies();
        DateService dateService = new DateService();
        HashMap<String, String> cookies1 = cookies.getCookies();
        try {
            Document doc = Jsoup.connect("http://dbs/eva/Requests/ReqList?NeedSearch=True&CreateTimeFrom=17.10.2022&CreateTimeTo=23.10.2022&ReqType=5&PageSize=100&BrokenDeadlines=false&IsNoRecordsMSE=false&Page=1")
                    .userAgent("Chrome/4.0.249.0 Safari/532.5")
                    .cookies(cookies1)
                    .get();
            String text = doc.selectXpath("//*[@id=\"grid\"]/div[2]/span/strong").text();
            System.out.println(text);
            int p = 0;
            for (int i = 1; i < (Integer.parseInt(text) / 100) + 2; i++) {
                doc = Jsoup.connect("http://dbs/eva/Requests/ReqList?NeedSearch=True&CreateTimeFrom=17.10.2022&CreateTimeTo=23.10.2022&ReqType=5&PageSize=100&BrokenDeadlines=false&IsNoRecordsMSE=false&Page=" + i)
                        .userAgent("Chrome/4.0.249.0 Safari/532.5")
                        .cookies(cookies1)
                        .get();

                Elements select = doc.select("#table-container > table");
                Elements tr = select.select("tr");
                System.out.println(tr.size());
                for (int ii = 1; ii < tr.size(); ii++) {
                    Elements td = tr.get(ii).select("td");
                    insertOrUpdateDirection(td);
                }
            }
            System.out.println(p);

        } catch (IOException e) {

        }
    }

    public void insertOrUpdateDirection(Elements elements) {
        Direction build = Direction.builder()
                .url("http://dbs" + (elements.get(0).select("a").attr("href")))
                .remdId(elements.get(3).text())
                .regNum(elements.get(4).text())
                .regDate(dateService.toCorrectStringDate(elements.get(5).text()))
                .Stage(elements.get(6).text())
                .createTime(dateService.toCorrectStringDate(elements.get(8).text()))
                .refIssDate(dateService.toCorrectStringDate(elements.get(9).text()))
                .refOrgName(elements.get(11).text())
                .refOrgOgrn(elements.get(12).text())
                .fio(elements.get(13).text())
                .birthDate(dateService.toCorrectStringDate(elements.get(14).text()))
                .repKind(elements.get(22).text())
                .recDate(dateService.toCorrectStringDate(elements.get(23).text()))
                .build();
        if (directionDao.getCountDirectionByUrl(build.getUrl()) == 0) {
            directionDao.insertDirection(build);
        } else {
            if (directionDao.getStatusDirectionByUrl(build.getUrl())) {
                directionDao.updateDirection(build);
            }
        }
    }

    public String dateInsertEva(String url) {
        String[] split = url.split("/");
        GetCookies cookies = new GetCookies();
        HashMap<String, String> cookies1 = cookies.getCookies();
        try {
            Document doc = Jsoup.connect("http://dbs/eva/Documents/History/" + split[split.length - 1])
                    .userAgent("Chrome/4.0.249.0 Safari/532.5")
                    .cookies(cookies1)
                    .get();

            Elements select = doc.select("#table-history > tbody");
            Elements tr = select.select("tr");
            for (Element element : tr) {
                if (element.select("td").get(3).text().equals("Направление получено из РЭМД")) {
                    return element.select("td").get(1).text();
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "null";
    }
}