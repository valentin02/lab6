package sample;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class Minfin extends Thread {
    private List<Currency> minfin = new ArrayList<>();

    public List<Currency> getMinfin() {
        return minfin;
    }

    public void add(Currency c) {
        minfin.add(c);
    }

    @Override
    public String toString() {
        String s = "Rates";//= minfin.toString();
        for (int i = 0; i < minfin.size(); i++) {
            s += minfin.get(i);
        }
        return s;
    }

    public void loadByURL(String url) throws IOException {
        minfin.clear();
        StringBuilder jsonIn = new StringBuilder();
        InputStream is = null;
        is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));

            int cp;
            while ((cp = rd.read()) != -1) {
                jsonIn.append((char) cp);
            }
        } finally {
            is.close();

        }
        String l = jsonIn.toString();

        ObjectMapper objectMapper = new ObjectMapper();
        int start, finish, index, z = 0;
        for (int i = 0; i < l.length(); i++) {
            if (l.charAt(i) == '{') {
                ++z;
            }

        }

        for (int i = 0; i < z; i++) {
            start = 0;
            finish = 0;
            index = 0;

            for (; index < l.length(); index++) {
                if (l.charAt(index) == '{') {
                    start = index;
                    break;

                }
            }
            for (; index < l.length(); index++) {
                if (l.charAt(index) == '}') {
                    finish = ++index;
                    break;
                }
            }
            minfin.add(objectMapper.readValue(l.substring(start, finish), Currency.class));
            l = l.substring(index, l.length());
        }

    }

    //@Override
    public void run(String url) throws IOException {
        this.loadByURL(url);
        super.run();
    }

    public Minfin filterByCurrency(String currency) {
        Minfin minfin1 = new Minfin();
        for (Currency c : minfin
        ) {
            if (c.getCurrency().equals(currency.toLowerCase()))
                minfin1.add(c);
        }
        return minfin1;
    }
}




