package top.liewyoung.network;

/**
 *
 * @author LiewYoung
 * @since 2025/12/1
 */

// TODO 2025/12/1 Liew.Y : 需要完成网络请求的post、get方法以实现模型调用
public class Requests {

    public Requests(String apiKey, String model,String baseUrl) {
        header = new Header(apiKey, model);
        this.url = baseUrl;
    }

    protected Header header;
    protected String url;
}


