package com.shuishu.utils.tool.file.enums;



/**
 * @Author ：谁书-ss
 * @Date ：2024/7/2 17:05
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @Description ：文件类型
 * <p></p>
 */
public enum FileTypeEnums {
    _HTML("html", "text/html"),
    _HTM("htm", "text/html"),
    _SHTML("shtml", "text/html"),
    _CSS("css", "text/css"),
    _XML("xml", "text/xml"),
    _MML("mml", "text/mathml"),
    _TXT("txt", "text/plain"),
    _JAD("jad", "text/vnd.sun.j2me.app-descriptor"),
    _WML("wml", "text/vnd.wap.wml"),
    _HTC("htc", "text/x-component"),

    _GIF("gif", "gif"),
    _JPEG("jpeg", "image/jpeg"),
    _JPG("jpg", "image/jpeg"),
    _AVIF("avif", "image/avif"),
    _PNG("png", "image/png"),
    _SVG("svg", "image/svg+xml"),
    _SVGZ("svgz", "image/svg+xml"),
    _TIFF("tiff", "image/tiff"),
    _TIF("tif", "image/tiff"),
    _WBMP("wbmp", "image/vnd.wap.wbmp"),
    _WEBP("webp", "image/webp"),
    _ICO("ico", "image/x-icon"),
    _JNG("jng", "image/x-jng"),
    _BMP("bmp", "image/x-ms-bmp"),

    _MID("mid", "audio/midi"),
    _MIDI("midi", "audio/midi"),
    _KAR("kar", "audio/midi"),
    _MP3("mp3", "audio/mpeg"),
    _OGG("ogg", "audio/ogg"),
    _M4A("m4a", "audio/x-m4a"),
    _RA("ra", "audio/x-realaudio"),

    _3GPP("3gpp", "video/3gpp"),
    _3GP("3gp", "video/3gp"),
    _MP2T("ts", "video/mp2t"),
    _MP4("mp4", "video/mp4"),
    _MPEG("mpeg", "video/mpeg"),
    _MPG("mpg", "video/mpeg"),
    _MOV("mov", "video/quicktime"),
    _WEBM("webm", "video/webm"),
    _FLV("ra", "video/x-flv"),
    _M4V("m4v", "video/x-m4v"),
    _MNG("mng", "video/x-mng"),
    _ASX("asx", "video/x-ms-asf"),
    _ASF("asf", "video/x-ms-asf"),
    _WMV("wmv", "video/x-ms-wmv"),
    _AVI("avi", "video/x-msvideo"),

    _WOFF("woff", "font/woff"),
    _WOFF2("woff2", "font/woff2"),

    _JS("js", "application/javascript"),
    _ATOM("atom", "application/atom+xml"),
    _RSS("rss", "application/rss+xml"),
    _JAR("jar", "application/java-archive"),
    _WAR("war", "application/java-archive"),
    _EAR("ear", "application/java-archive"),
    _JSON("json", "application/json"),
    _HQX("hqx", "application/mac-binhex40"),
    _DOC("doc", "application/msword"),
    _DOCX("docx", "application/vnd.openxmlformats-officedocument.wordprocessingml.document"),
    _PDF("pdf", "application/pdf"),
    _PS("ps", "application/postscript"),
    _EPS("eps", "application/postscript"),
    _AI("ai", "application/postscript"),
    _RTF("rtf", "application/rtf"),
    _M3U8("m3u8", "application/vnd.apple.mpegurl"),
    _KML("kml", "application/vnd.google-earth.kml+xml"),
    _KMZ("kmz", "application/vnd.google-earth.kmz"),
    _XLS("xls", "application/vnd.ms-excel"),
    _XLSX("xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"),
    _EOT("eot", "application/vnd.ms-fontobject"),
    _PPT("ppt", "application/vnd.ms-powerpoint"),
    _PPTX("pptx", "application/vnd.openxmlformats-officedocument.presentationml.presentation"),
    _ODG("odg", "application/vnd.oasis.opendocument.graphics"),
    _ODP("odp", "application/vnd.oasis.opendocument.presentation"),
    _ODS("ods", "application/vnd.oasis.opendocument.spreadsheet"),
    _ODT("odt", "application/vnd.oasis.opendocument.text"),
    _WMLC("wmlc", "application/vnd.wap.wmlc"),
    _WASM("wasm", "application/wasm"),
    _7Z("7z", "application/x-7z-compressed"),
    _CCO("cco", "application/x-cocoa"),
    _JARDIFF("jardiff", "application/x-java-archive-diff"),
    _JNLP("jnlp", "application/x-java-jnlp-file"),
    _RUN("run", "application/x-makeself"),
    _PL("pl", "application/x-perl"),
    _PM("pm", "application/x-perl"),
    _PRC("prc", "application/x-pilot"),
    _PDB("pdb", "application/x-pilot"),
    _RAR("rar", "application/x-rar-compressed"),
    _RPM("rpm", "application/x-redhat-package-manager"),
    _SEA("sea", "application/x-sea"),
    _SWF("swf", "application/x-shockwave-flash"),
    _SIT("sit", "application/x-stuffit"),
    _TCL("tcl", "application/x-tcl"),
    _TK("tk", "application/x-tcl"),
    _DER("der", "application/x-x509-ca-cert"),
    _PEM("pem", "application/x-x509-ca-cert"),
    _CRT("crt", "application/x-x509-ca-cert"),
    _XPI("xpi", "application/x-xpinstall"),
    _XHTML("xhtml", "application/xhtml+xml"),
    _XSPF("xspf", "application/xspf+xml"),
    _ZIP("zip", "application/zip"),
    _BIN("bin", "application/octet-stream"),
    _EXE("exe", "application/octet-stream"),
    _DLL("dll", "application/octet-stream"),
    _DEB("deb", "application/octet-stream"),
    _DMG("dmg", "application/octet-stream"),
    _ISO("iso", "application/octet-stream"),
    _IMG("img", "application/octet-stream"),
    _MSI("msi", "application/octet-stream"),
    _MSP("msp", "application/octet-stream"),
    _MSM("msm", "application/octet-stream"),

    ;

    private String suffix;

    private String type;

    FileTypeEnums() {
    }

    FileTypeEnums(String suffix, String type) {
        this.suffix = suffix;
        this.type = type;
    }


    public static String getTypeBySuffix(String suffix) {
        if (suffix == null || suffix.isEmpty()) {
            for (FileTypeEnums value : values()) {
                if (value.suffix.equalsIgnoreCase(suffix)) {
                    return value.type;
                }
            }
        }
        return null;
    }


    public String getSuffix() {
        return suffix;
    }

    public String getType() {
        return type;
    }
}
