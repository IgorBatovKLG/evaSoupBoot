package ru.batov.evasoupboot.services;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;

@Service
public class Main {

    private final ConfigurableApplicationContext context;

    public Main(ConfigurableApplicationContext context) {
        this.context = context;
    }


    public void insertOrUpdateDirection() {

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
                    System.out.println(td.get(0).text());
                    System.out.println(td.get(1).text());
                    System.out.println(td.get(2).text());
                    System.out.println(td.get(3).text());
                    System.out.println(td.get(4).text());
                }
            }
            System.out.println(p);

        } catch (IOException e) {

        }
    }




}