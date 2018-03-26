package androidAutoLayoutGenerate;

/**
 * Android Autolayout百分比适配方案（加小改进）,用来生成不同分辨率的value的工具
 */
public class AutoLayoutPlus {
	

    public static void main(String[] args) {
        int baseW = 375;
        int baseH = 667;
        String addition = "";
        try {
            if (args.length >= 3) {
                baseW = Integer.parseInt(args[0]);
                baseH = Integer.parseInt(args[1]);
                addition = args[2];
            } else if (args.length >= 2) {
                baseW = Integer.parseInt(args[0]);
                baseH = Integer.parseInt(args[1]);
            } else if (args.length >= 1) {
                addition = args[0];
            }
        } catch (NumberFormatException e) {

            System.err
                    .println("right input params : java -jar xxx.jar width height w,h_w,h_..._w,h;");
            e.printStackTrace();
            System.exit(-1);
        }

        new AutoLayoutResGenerate(baseW, baseH, addition);
    }
    


}

