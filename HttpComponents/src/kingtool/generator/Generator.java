package kingtool.generator;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Generator {
	public static String NAME = new String("颖灵英艺芸穗颖秋茂荣科秦莲英芝芹深澜江池潮萍沛潜鸿汉毅独刚强衡韧恒坚力决定立主志意睿锐哲慧敦迪明晓显悉晰维学思悟析文书勤俊英健壮焕挺帅秀伟武雄松柏山石婵娟姣妯婷姿媚婉丽妩美倩兰安静顺通坦泰然宁定和康");
	public static char[] SEX = new char[]{'男','女'};
	private static Random random = new Random();
	public static  String getName(){
		StringBuilder sb  = new StringBuilder();
		for(int i = 0 ; i< 3; i++){
			sb.append( NAME.charAt(random.nextInt(NAME.length())) );
		}
		return sb.toString();
	}
	
	public static char getSex(){
		return SEX[random.nextInt(2)];
	}
	
	public static String getIdCard(){
		String s = new String("33062"+Math.random()).replace('.', '7').substring(0,18);
		return s;
	}
	
	public static String getCellphone(){
		String s = new String("138"+(1000+random.nextInt(900))+(1000+random.nextInt(7000)));
		return s;
	}
	
	public static String getTelephone(){
		String s = new String("0575-846"+(10000+random.nextInt(88888)));
		return s;
	}
	
	public static String getEmail(){
		String s = new String(""+random.nextInt(999999999)+"@qq.com");
		return s;
	}
	
	public static String getInDay(){
		String s = new String(""+(1988+random.nextInt(25))+"-"+random.nextInt(12)+"-"+random.nextInt(28));
		return s;
	}
	
	public static String getDateTime(){
		String s = new String(""+(1988+random.nextInt(25))+"-"+random.nextInt(12)+"-"+random.nextInt(28)+" "+random.nextInt(12)+":"+random.nextInt(60)+":"+random.nextInt(60));
		return s;
	}
	
	public static String getCarNumber(){
		int i = 1000+random.nextInt(9000);
		return new String(""+i);
	}
	
	public static String getOddEven(){
		return new Integer(random.nextInt(2)).toString();
	}
	public static void main(String[] args) {
		System.out.println(getName());
//		System.out.println(getSex());
//		System.out.println(getIdCard());
//		System.out.println(getCellphone());
//		System.out.println(getTelephone());
//		System.out.println(getEmail());
//		System.out.println(getInDay());
//		System.out.println(getDateTime());
//		System.out.println(getCarNumber());
//		System.out.println(getOddEven());
	}
	
}


