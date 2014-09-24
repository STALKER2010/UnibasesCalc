package andrews.ucalc;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

public class Main {
    public static final void main(String[] args) {
    	try {
    		try {
    		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
    		        if ("Nimbus".equals(info.getName())) {
    		            UIManager.setLookAndFeel(info.getClassName());
    		            break;
    		        }
    		    }
    		} catch (Exception e) {
    		}
			UIFrame frame = new UIFrame();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
        /*final Long f = Long.valueOf(first, base);
        final Long s = Long.valueOf(second, base);
        final List<String> proc = new ArrayList<String>();
        if (action.equals("*")) {
            for (Character c: second.toCharArray()) {
                final Long cl = Chars.c2i(c, base);
                proc.add(Long.toString(cl*f,base));
            }
            final Long r = f * s;
            for (int i=proc.size()-1; i>=0; i--) {
                System.out.println(proc.get(i));
            }
            System.out.println(Long.toString(r, base));
        } else if (action.equals("/")) {
            for (Character c: second.toCharArray()) {
                final Long cl = Chars.c2i(c, base);
                proc.add(Long.toString(cl/f,base)+":"+Long.toString(cl%f,base));
            }
            final Long r = f / s;
            final Long rm = f % s;
            for (int i=proc.size()-1; i>=0; i--) {
                System.out.println(proc.get(i));
            }
            System.out.println(Long.toString(r, base)+":"+Long.toString(rm, base));
        }*/
    }
}
