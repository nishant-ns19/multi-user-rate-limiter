import factory.RateLimiterFactory;
import service.MultiUserRateLimiter;

public class Main {

    public static void main(String[] args) throws InterruptedException {
//        RateLimiter rateLimiter = RateLimiterFactory.getDefaultRateLimiter(100, 10);
//        for(int i = 0 ;i<100;i ++) {
//            rateLimiter.hit();
//        }
//        Thread.sleep(1000);
//        for(int i = 0 ;i<100;i ++) {
//            rateLimiter.hit();
//        }

        MultiUserRateLimiter multiUserRateLimiter = new MultiUserRateLimiter(new RateLimiterFactory(100, 10));
        for(int i = 0 ;i<100;i ++) {
            multiUserRateLimiter.hit(Integer.toString(i%5));
        }
        Thread.sleep(1000);
        for(int i = 0 ;i<100;i ++) {
            multiUserRateLimiter.hit(Integer.toString(i%5));
        }
    }
}