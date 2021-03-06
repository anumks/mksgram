/**
 * Copyright (C) 2016 Bruno Candido Volpato da Cunha (brunocvcunha@gmail.com)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package me.jpomykala.javagram.util;

import lombok.SneakyThrows;
import me.jpomykala.javagram.ApiConstants;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Utilities related to hash
 *
 * @author Bruno Candido Volpato da Cunha
 */
public class InstagramHashUtil {
  /**
   * The characters from a hex-string
   */
  public static final String XLATE = "0123456789abcdef";

  /**
   * Digest a string using the given codec and input
   *
   * @param codec  Codec to use
   * @param source Source to use
   * @return
   */
  protected static String digest(String codec, String source) {
    try {
      MessageDigest digest = MessageDigest.getInstance(codec);
      byte[] digestBytes = digest.digest(source.getBytes(Charset.forName("UTF-8")));
      return hexlate(digestBytes, digestBytes.length);
    } catch (NoSuchAlgorithmException nsae) {
      throw new RuntimeException(codec + " codec not available");
    }
  }

  /**
   * Get the MD5 (in hexadecimal presentation) for the given source
   *
   * @param source The string to hash
   * @return MD5 hex presentation
   */
  public static String md5hex(String source) {
    return digest("MD5", source);
  }

  /**
   * Convert the byte array to a hexadecimal presentation (String)
   *
   * @param bytes        byte array
   * @param initialCount count (length) of the input
   * @return
   */
  protected static String hexlate(byte[] bytes, int initialCount) {
    if (bytes == null) {
      return "";
    }

    int count = Math.min(initialCount, bytes.length);
    char[] chars = new char[count * 2];

    for (int i = 0; i < count; ++i) {
      int val = bytes[i];
      if (val < 0) {
        val += 256;
      }
      chars[(2 * i)] = XLATE.charAt(val / 16);
      chars[(2 * i + 1)] = XLATE.charAt(val % 16);
    }

    return new String(chars);
  }

  /**
   * Generates Instagram Device ID
   *
   * @param username Username to generate
   * @param password Password to generate
   * @return device id
   */
  public static String generateDeviceId(String username, String password) {
    String seed = md5hex(username + password);
    String volatileSeed = "12345";

    return "android-" + md5hex(seed + volatileSeed).substring(0, 16);
  }

  /**
   * Generate a Hmac SHA-256 hash
   *
   * @param key    key
   * @param string value
   * @return hashed
   */
  public static String generateHash(String key, String string) {
    SecretKeySpec object = new SecretKeySpec(key.getBytes(), "HmacSHA256");
    try {
      Mac mac = Mac.getInstance("HmacSHA256");
      mac.init(object);
      byte[] byteArray = mac.doFinal(string.getBytes(StandardCharsets.UTF_8));
      return new String(new Hex().encode(byteArray), StandardCharsets.ISO_8859_1);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * Generate signed payload
   *
   * @param payload Payload
   * @return Signed string
   */
  @SneakyThrows
  public static String generateSignature(String payload) {
    String parsedData = URLEncoder.encode(payload, StandardCharsets.UTF_8);

    String signedBody = generateHash(ApiConstants.API_KEY, payload);

    return "ig_sig_key_version=" + ApiConstants.API_KEY_VERSION + "&signed_body=" + signedBody + '.'
            + parsedData;

  }
}
