package cn.flights.unitls;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * ���ܹ���
 * @author Administrator
 *
 */
public class PasswordUtil {

	
	/**
	 * Md5����
	 * @param str ԭʼ���� 
	 * @param salt	����
	 * @return
	 */
	public static String md5(String str,String salt){
		return new Md5Hash(str,salt).toString();
	}
	
	public static void main(String[] args) {
		String password="000000";
		//���û�����Ϊ����
		System.out.println("Md5���ܣ�"+PasswordUtil.md5(password, "admin"));
	}
}
