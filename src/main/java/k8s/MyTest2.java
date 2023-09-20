package k8s;

import io.kubernetes.client.Exec;
import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.Configuration;
import io.kubernetes.client.util.Config;
import io.kubernetes.client.util.Streams;
import java.io.IOException;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 * @author yang.yonglian
 * @version 1.0.0
 * @Description TODO
 * @createTime 2022/9/20 15:09
 */

public class MyTest2 {
    public static void main(String[] args)
            throws IOException, ApiException, InterruptedException, ParseException {
        final Options options = new Options();
        options.addOption(new Option("p", "pod", true, "The name of the pod"));
        options.addOption(new Option("n", "namespace", true, "The namespace of the pod"));

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);

        String podName = cmd.getOptionValue("p", "call-mysql-0");
        String namespace = cmd.getOptionValue("n", "default");
        args = cmd.getArgs();

        ApiClient client = Config.fromConfig("D:\\config");
        Configuration.setDefaultApiClient(client);

        Exec exec = new Exec();
        boolean tty = System.console() != null;
        // final Process proc = exec.exec("default", "nginx-4217019353-k5sn9", new String[]
        //   {"sh", "-c", "echo foo"}, true, tty);
        String[] commands = new String[] {"sh", "-c", "echo foo"};
        System.out.println(tty);
//        commands =  args.length == 0 ? new String[] {"sh"} : args;
        final Process proc =
                exec.exec(namespace, podName, commands, true, tty);

        Thread in =
                new Thread(
                        new Runnable() {
                            public void run() {
                                try {
                                    Streams.copy(System.in, proc.getOutputStream());
                                } catch (IOException ex) {
                                    ex.printStackTrace();
                                }
                            }
                        });
        in.start();

        Thread out =
                new Thread(
                        new Runnable() {
                            public void run() {
                                try {
                                    System.out.println("------");
                                    Streams.copy(proc.getInputStream(), System.out);
                                } catch (IOException ex) {
                                    ex.printStackTrace();
                                }
                            }
                        });
        out.start();

        proc.waitFor();

        // wait for any last output; no need to wait for input thread
        out.join();

        proc.destroy();

        System.exit(proc.exitValue());
    }
}
