

import java.util.Locale;
import java.util.Random;

import com.github.javafaker.Faker;

public class Main {
    enum Language{
        en_US,
        ru_RU,
        be_BY
    }

    private static long nodeCount;
    private static double errorPercent;
    private static Language currentLanguage=null;
    private static Faker en_faker = new Faker();
    private static Faker by_faker = new Faker(new Locale("by"));
    private static Faker ru_faker = new Faker(new Locale("ru"));
    static Random r = new Random();

    public static void main(String[] args) {
        try {
            if(args.length<2)throw new Exception("program needs at least 2 args!");
            currentLanguage = Language.valueOf(args[0]);
            if(currentLanguage==null)throw new Exception("incorrect. first argument - Language(en_US,ru_RU,be_BY)");
            nodeCount = Long.parseLong(args[1]);
            if(args.length>2) {
                errorPercent = Double.parseDouble(args[2]);
            }else {
                errorPercent = 0;
            }
            generateFakes(currentLanguage,nodeCount,errorPercent);
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    private static void generateFakes(Language currentLanguage, long nodeCount, double errorPercent) {
        for(int i=0;i<nodeCount;i++) {
            String s = getRecord(currentLanguage);
            s = generateErrors(s,errorPercent,currentLanguage);
            System.out.println(s);
        }
    }

    private static String generateErrors(String s,double e,Language currentLanguage) {

        s = makeError(s,(int)Math.floor(e),currentLanguage);

        if(r.nextDouble()<e-Math.floor(e)){
            s = makeError(s,1,currentLanguage);
        }
        return s;
    }

    private static String getRecord(Language currentLanguage) {
        if(currentLanguage == Language.en_US || currentLanguage == Language.ru_RU)
        return getFIO(currentLanguage)+"; "+getAddress(currentLanguage)+"; "+getPhone(currentLanguage);
        else {
            String s = (getFIO(currentLanguage)+"; "+getAddress(currentLanguage)+"; "+getPhone(currentLanguage)).replaceAll("і","i");
            s.replaceAll("І","I");
            return s;
        }
    }

    private static String makeError(String s, int i, Language l) {

        for(int j=0;j<i;j++) {
            int type = r.nextInt(3);
            if(type==1) {

                int pos = r.nextInt(s.length()-1);
                s = s.substring(0, pos) + s.substring(pos + 1);

            }else if(type==0) {
                for(int j1=0;j1<r.nextInt(2)+1;j1++)
                if(l == Language.en_US) {
                    int pos = r.nextInt(s.length());
                    char[] sAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890".toCharArray();
                    s = s.substring(0, pos) +sAlphabet[r.nextInt(sAlphabet.length)]+ s.substring(pos);
                }else if(l == Language.ru_RU) {
                    int pos = r.nextInt(s.length());
                    int p = r.nextInt(74);
                    String c="";
                    if(p>64) {
                        c = (p - 64) + "";
                    }else
                    c=(char)(r.nextInt(64)+1040)+"";

                    s = s.substring(0, pos) +c+ s.substring(pos);
                }else if(l == Language.be_BY) {
                    int pos = r.nextInt(s.length());
                    int p = r.nextInt(74);
                    String c="";
                    if(p>64) {
                        c = (p - 64) + "";
                    }else
                        c=(char)(r.nextInt(64)+1040)+"";

                    s = s.substring(0, pos) +c+ s.substring(pos);
                }

            }else {
                int pos = r.nextInt(s.length());
                if(s.length()==1)continue;
                if(pos<s.length()-1) {
                    char[]array = s.toCharArray();
                    char c= array[pos];
                    array[pos]=array[pos+1];
                    array[pos+1]=c;
                    s = new StringBuilder().append(array).toString();
                }else {
                    char[] array = s.toCharArray();
                    char c = array[pos];
                    array[pos] = array[pos - 1];
                    array[pos - 1] = c;
                    s = new StringBuilder().append(array).toString();
                }
            }
        }
        return s;
    }

    private static String getPhone(Language currentLanguage2) {
        if(currentLanguage2 == Language.ru_RU) {
            return ru_faker.phoneNumber().phoneNumber();
        }else if(currentLanguage2 == Language.en_US) {
            return en_faker.phoneNumber().phoneNumber();
        }else{
            return by_faker.phoneNumber().phoneNumber();
        }
    }

    private static String getAddress(Language currentLanguage2) {
        if(currentLanguage2 == Language.ru_RU) {
            String s = ru_faker.address().fullAddress();
            String s1="";
            for(int i=0;i<s.length();i++){
                if(i<6){
                    s1+=r.nextInt(10);
                }else{
                    s1+=s.charAt(i);
                }
            }
            return s1;
        }else if(currentLanguage2 == Language.en_US) {
            return en_faker.address().fullAddress();
        }else{
            String s = by_faker.address().fullAddress();
            String s1="";
            for(int i=0;i<s.length();i++){
                if(i<6){
                    s1+=r.nextInt(10);
                }else{
                    s1+=s.charAt(i);
                }
            }
            return s1;
        }
    }

    private static String getFIO(Language currentLanguage2) {
        if(currentLanguage2 == Language.en_US) {
            return en_faker.name().fullName();
        }else if(currentLanguage2 == Language.ru_RU) {
            return ru_faker.name().fullName();
        }else{
            return by_faker.name().fullName();
        }
    }
}
