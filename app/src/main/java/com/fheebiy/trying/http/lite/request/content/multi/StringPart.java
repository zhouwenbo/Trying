package com.fheebiy.trying.http.lite.request.content.multi;

import com.fheebiy.trying.http.lite.data.Consts;
import com.fheebiy.trying.http.lite.utils.StringCodingUtils;

import java.io.UnsupportedEncodingException;

/**
 * @author MaTianyu
 * @date 14-7-29
 */
public class StringPart extends BytesPart {
    protected String charset;
    protected String mimeType;

    public StringPart(String key, String string) {
        this(key, string, Consts.DEFAULT_CHARSET, Consts.MIME_TYPE_TEXT);
    }

    public StringPart(String key, String string, String charset, String mimeType) {
        super(key, getBytes(string, charset),mimeType);
        this.mimeType = mimeType != null ? mimeType : Consts.MIME_TYPE_TEXT;
        this.charset = charset;
    }

    public static byte[] getBytes(String string, String charset) {
        try {
            return string.getBytes(charset);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected byte[] createContentType() {
        return StringCodingUtils.getBytes(Consts.CONTENT_TYPE + ": " + mimeType + " " + Consts.CHARSET_PARAM + charset +
                "\r\n", infoCharset);
    }

    @Override
    public byte[] getTransferEncoding() {
        return TRANSFER_ENCODING_8BIT;
    }
}
