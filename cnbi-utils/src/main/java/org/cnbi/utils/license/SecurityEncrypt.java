package org.cnbi.utils.license;
import com.sun.crypto.provider.SunJCE;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
public class SecurityEncrypt
{
  private SecretKey deskey;
  private Cipher cipher;
  private byte[] encryptorData;
  private byte[] decryptorData;

  public SecurityEncrypt()
  {
    init();
  }

  public void init() {
    Security.addProvider(new SunJCE());
    try
    {
      byte[] key = "wk2m$i~w".getBytes();
      this.deskey = new SecretKeySpec(key, "DES");

      this.cipher = Cipher.getInstance("DES");
    } catch (NoSuchAlgorithmException ex) {
      ex.printStackTrace();
    } catch (NoSuchPaddingException ex) {
      ex.printStackTrace();
    }
  }

  public byte[] createEncryptor(byte[] datasource) {
    return createEncryptor(datasource, "wk2m$i~w");
  }

  public byte[] createEncryptor(byte[] datasource, String key) {
    try {
      this.cipher.init(1, new SecretKeySpec(key.getBytes(), "DES"));
      this.encryptorData = this.cipher.doFinal(datasource);
    } catch (InvalidKeyException ex) {
      ex.printStackTrace();
    } catch (BadPaddingException ex) {
      ex.printStackTrace();
    } catch (IllegalBlockSizeException ex) {
      ex.printStackTrace();
    }
    return this.encryptorData;
  }

  public byte[] createEncryptor(String datasource) throws Exception {
    return createEncryptor(datasource.getBytes());
  }

  public byte[] createDecryptor(byte[] datasource) throws Exception {
    try {
      this.cipher.init(2, this.deskey);
      this.decryptorData = this.cipher.doFinal(datasource);
    } catch (InvalidKeyException ex) {
      ex.printStackTrace();
    } catch (BadPaddingException ex) {
      ex.printStackTrace();
    } catch (IllegalBlockSizeException ex) {
      ex.printStackTrace();
    } catch (Exception ex) {
      ex.printStackTrace();
    }

    return this.decryptorData;
  }

  public String byteToString(byte[] dataByte) {
    String returnStr = null;
    BASE64Encoder be = new BASE64Encoder();
    returnStr = be.encode(dataByte);
    return returnStr;
  }

  public byte[] stringToByte(String datasource) throws Exception
  {
    BASE64Decoder bd = new BASE64Decoder();
    byte[] sorData = bd.decodeBuffer(datasource);
    return sorData;
  }

  public void printByte(byte[] data)
  {
    for (int i = 0; i < data.length; i++);
  }
}