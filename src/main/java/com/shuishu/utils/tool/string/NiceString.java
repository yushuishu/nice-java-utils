package com.shuishu.utils.tool.string;


import javax.annotation.CheckForNull;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @Author ：谁书-ss
 * @Date ：2024/6/18 10:36
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @Description ：字符串操作
 * <p></p>
 * 参考：
 */
public class NiceString {

    public static String lenientFormat(@CheckForNull String template, @CheckForNull Object... args) {
        template = String.valueOf(template);
        if (args == null) {
            args = new Object[]{"(Object[])null"};
        } else {
            for(int i = 0; i < args.length; ++i) {
                args[i] = lenientToString(args[i]);
            }
        }

        StringBuilder builder = new StringBuilder(template.length() + 16 * args.length);
        int templateStart = 0;

        int i;
        int placeholderStart;
        for(i = 0; i < args.length; templateStart = placeholderStart + 2) {
            placeholderStart = template.indexOf("%s", templateStart);
            if (placeholderStart == -1) {
                break;
            }

            builder.append(template, templateStart, placeholderStart);
            builder.append(args[i++]);
        }

        builder.append(template, templateStart, template.length());
        if (i < args.length) {
            builder.append(" [");
            builder.append(args[i++]);

            while(i < args.length) {
                builder.append(", ");
                builder.append(args[i++]);
            }

            builder.append(']');
        }

        return builder.toString();
    }

    private static String lenientToString(@CheckForNull Object o) {
        if (o == null) {
            return "null";
        } else {
            try {
                return o.toString();
            } catch (Exception var3) {
                String objectToString = o.getClass().getName() + '@' + Integer.toHexString(System.identityHashCode(o));
                Logger.getLogger("com.google.common.base.Strings").log(Level.WARNING, "Exception during lenientFormat for " + objectToString, var3);
                return "<" + objectToString + " threw " + var3.getClass().getName() + ">";
            }
        }
    }

}
