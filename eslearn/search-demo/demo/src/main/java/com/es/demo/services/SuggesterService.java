package com.es.demo.services;

import java.util.*;

import javax.annotation.PostConstruct;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.es.demo.models.Option;

import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

import org.apache.http.HttpStatus;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.*;

@Slf4j
@Service
public class SuggesterService {

    private final String suggesterTemplate="{%n" +
            "  \"suggest\": {%n" +
            "    \"firstname-suggestion\": {%n" +
            "      \"text\": \"%s\",%n" +
            "      \"phrase\": {%n" +
            "        \"field\": \"firstname\",%n" +
            "        \"max_errors\":2,%n" +
            "        \"confidence\":0,%n" +
            "        \"direct_generator\":[{%n" +
            "          \"field\":\"firstname\",%n" +
            "          \"suggest_mode\":\"popular\"%n" +
            "        }%n" +
            "        ]%n" +
            "      }%n" +
            "    },%n" +
            "    \"lastname-suggestion\": {%n" +
            "      \"text\": \"%s\",%n" +
            "      \"phrase\": {%n" +
            "        \"field\": \"lastname\",%n" +
            "        \"max_errors\":2,%n" +
            "        \"confidence\":0,%n" +
            "        \"direct_generator\":[{%n" +
            "          \"field\":\"lastname\",%n" +
            "          \"suggest_mode\":\"popular\"%n" +
            "        }%n" +
            "        ]%n" +
            "      }%n" +
            "    },%n" +
            "    \"address-suggestion\": {%n" +
            "      \"text\": \"%s\",%n" +
            "      \"phrase\": {%n" +
            "        \"field\": \"address\",%n" +
            "        \"max_errors\":2,%n" +
            "        \"confidence\":0,%n" +
            "        \"direct_generator\":[{%n" +
            "          \"field\":\"address\",%n" +
            "          \"suggest_mode\":\"popular\"%n" +
            "        }%n" +
            "        ]%n" +
            "      }%n" +
            "    }%n" +
            "  }%n" +
            "}";

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    private RestClient lowLevelClient;


    @PostConstruct
    public void init() {
        lowLevelClient = restHighLevelClient.getLowLevelClient();
    }

    public List<String> getSuggestedWords(String input) {
        List<String> list = new ArrayList<>();
        String requestContent = String.format(suggesterTemplate, input, input, input);
        Request request = new Request("POST", "/bank/_doc/_search");
        request.setJsonEntity(requestContent);
        Response resp;
        try {
            resp = lowLevelClient.performRequest(request);
        } catch(Exception e) {
            log.warn("fail to get info from es server", e);
            // 因为接口主要是获取自动补全提示信息，获取不到，不报错。
            return list;
        }
        int respCode = resp.getStatusLine().getStatusCode();
        if (respCode != HttpStatus.SC_OK) {
            log.warn("[SuggesterService]fail to get info from Elasticsearch server");
            return list;
        }
        
        String responseBody;
        try {
            responseBody = EntityUtils.toString(resp.getEntity());
            log.debug(responseBody);
        } catch (Exception e) {
            log.error("fail to convert resp to json", e);
            return list;
        }
        this.processJsonReesult(responseBody, list);
        return list;
    }

    private void processJsonReesult(String responseBody, List<String> resultList) {
        JSONObject jsonObject = JSON.parseObject(responseBody);
        JSONObject suggestObject = jsonObject.getJSONObject("suggest");
        Set<Option> set =  new HashSet<>();
        for (Map.Entry<String, Object> e : suggestObject.entrySet()) {
            log.debug(e.getKey());
            JSONArray array = (JSONArray)e.getValue();
            for (Object item : array) {
                JSONObject obj = (JSONObject)item;
                JSONArray options = (JSONArray)obj.get("options");
                for (Object optionStr : options) {
                    Option option = (Option)JSONObject.parseObject((((JSONObject)optionStr).toJSONString()), Option.class);
                    set.add(option);
                }
            }
        }
        for (Option option : set) {
            resultList.add(option.getText());
        }
    }
    
}
