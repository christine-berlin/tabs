package tabs;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.IntStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import javax.swing.JOptionPane;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class tabcreator {
    public static String fileName = "data.txt";
    public static JFrame myframe;
    public static JPanel panel, buttonpanel, buttonpanel2;
    public static JButton buttonselectmusicXML, buttonlowG, buttonhighG ,convertbutton, clearbutton, makeTABfile, barline, showAll, tabCombinations;
    public static Point mousePT;
    static int[] result = new int[]{-1,-1,-1,-1};
    public static int numberofnotes;
    public static int[][] tabmatrix;
    public static boolean bar2 = false;
    public static JFileChooser filechooser;
    public static File xmlfile;
    public static HashMap<String, Integer> hash = new HashMap<String, Integer>();
    public static HashMap<Integer, String> hash_tab = new HashMap<Integer, String>();
    public static BufferedImage imageuke, imageclef;
    public static JTextField tf4,tf1,tf2,tf3;
    public static JMenuBar mb;
    public static JPopupMenu pm;
    public static Boolean mbvalue = false;
    public static JLabel l1,l2,l3,l4;
    public static JPanel test1, test2,test3, test4;

    //{String4, String3, String2, String1}
    //LOW G
    public static int[][] PSaiten = new int[][]{
            {0,-1,-1,-1},
            {1,-1,-1,-1},
            {2,-1,-1,-1},
            {3,-1,-1,-1},
            {4,-1,-1,-1},
            {5,0,-1,-1},
            {6,1,-1,-1},
            {7,2,-1,-1},
            {8,3,-1,-1},
            {9,4,0,-1},
            {10,5,1,-1},
            {11,6,2,-1},
            {12,7,3,-1},
            {-1,8,4,-1},
            {-1,9,5,0},
            {-1,10,6,1},
            {-1,11,7,2},
            {-1,12,8,3},
            {-1,13,9,4},
            {-1,14,10,5},
            {-1,15,11,6},
            {-1,16,12,7},
            {-1,17,13,8},
            {-1,-1,14,9},
            {-1,-1,15,10},
            {-1,-1,-1,11},
            {-1,-1,-1,12},
            {-1,-1,-1,13},
            {-1,-1,-1,14},
            {-1,-1,-1,15},
    };

    public static int[][] PSaitenHighG = new int[][]{
            {-1,-1,-1,-1},
            {-1,-1,-1,-1},
            {-1,-1,-1,-1},
            {-1,-1,-1,-1},
            {-1,-1,-1,-1},

            {-1,0,-1,-1},
            {-1,1,-1,-1},
            {-1,2,-1,-1},
            {-1,3,-1,-1},
            {-1,4,0,-1},
            {-1,5,1,-1},
            {-1,6,2,-1},
            {0,7,3,-1},
            {1,8,4,-1},
            {2,9,5,0},
            {3,10,6,1},
            {4,11,7,2},
            {5,12,8,3},
            {6,13,9,4},
            {7,14,10,5},
            {8,15,11,6},
            {9,16,12,7},
            {10,17,13,8},
            {11,-1,14,9},
            {-1,-1,15,10},
            {-1,-1,-1,11},
            {-1,-1,-1,12},
            {-1,-1,-1,13},
            {-1,-1,-1,14},
            {-1,-1,-1,15},
    };

    public static void fill_hashtab()
    {
        hash_tab.put(-1, "-----");
        hash_tab.put(0, "--0--");
        hash_tab.put(1, "--1--");
        hash_tab.put(2, "--2--");
        hash_tab.put(3, "--3--");
        hash_tab.put(4, "--4--");
        hash_tab.put(5, "--5--");
        hash_tab.put(6, "--6--");
        hash_tab.put(7, "--7--");
        hash_tab.put(8, "--0--");
        hash_tab.put(9, "--9--");
        hash_tab.put(10, "-10--");
        hash_tab.put(11, "-11--");
        hash_tab.put(12, "-12--");
        hash_tab.put(13, "-13--");
        hash_tab.put(14, "-14--");
        hash_tab.put(15, "-15--");
        hash_tab.put(16, "-16--");
        hash_tab.put(17, "-17--");
    }

    public static void fill_hash_map()
    {
        hash.put("barline", -1);
        hash.put("G3no", 0);
        hash.put("G31", 1);
        hash.put("A3-1", 1);
        hash.put("A3no", 2);
        hash.put("A31", 3);
        hash.put("B3-1", 3);
        hash.put("B3no", 4);
        hash.put("B31", 15);
        hash.put("C4-1", 4);
        hash.put("C4no", 5);
        hash.put("C41", 6);
        hash.put("D4-1", 6);
        hash.put("D4no", 7);
        hash.put("D41", 8);
        hash.put("E4-1", 8);
        hash.put("E4no", 9);
        hash.put("E41", 10);
        hash.put("F4-1", 9);
        hash.put("F4no", 10);
        hash.put("F41", 11);
        hash.put("G4-1",11);
        hash.put("G4no", 12);
        hash.put("G41", 13);
        hash.put("A4-1", 13);
        hash.put("A4no", 14);
        hash.put("A41", 15);
        hash.put("B4-1", 15);
        hash.put("B4no", 16);
        hash.put("B41", 17);
        hash.put("C5-1",16);
        hash.put("C5no", 17);
        hash.put("C51", 18);
        hash.put("D5-1", 18);
        hash.put("D5no", 19);
        hash.put("D51", 20);
        hash.put("E5-1", 20);
        hash.put("E5no", 21);
        hash.put("E51", 22);
        hash.put("F5-1", 21);
        hash.put("F5no", 22);
        hash.put("F51", 23);
        hash.put("G5-1", 23);
        hash.put("G5no", 24);
        hash.put("G51",25);
        hash.put("A5-1", 25);
        hash.put("A5no", 26);
        hash.put("A51", 27);
        hash.put("B5-1", 27);
        hash.put("B5no", 28);
        hash.put("B51", 29);
        hash.put("C6-1", 28);
        hash.put("C6no", 29);
    }

    public static void save_tab()
    {
        if(!bar2)
            appendStrToFile(fileName,
                    hash_tab.get(MyPanel.tab[0])+hash_tab.get(MyPanel.tab[1])+hash_tab.get(MyPanel.tab[2])+hash_tab.get(MyPanel.tab[3]));
        if(bar2) appendStrToFile(fileName, "--|----|----|----|--");
        for(int i=0;i<MyPanel.P.length;i++)MyPanel.P[i] = false;
        for(int j=0;j<MyPanel.tab.length;j++)MyPanel.tab[j] = -1;
        for(int j=0;j<MyPanel.alltabs.length;j++) MyPanel.alltabs[j] = "";
        for(int k=0;k<result.length;k++)result[k] = -1;
        set_N_S_B_false();
        bar2=false;
    }

    public static void find_all_tabs() {
    	ArrayList notes = new ArrayList();
    	initialize_notes(notes);

        tabmatrix = new int[notes.size()][4];

        for(int v=0;v<notes.size();v++)
        {
            int t= (int) notes.get(v); 
            if(MyPanel.lowG) tabmatrix[v]=PSaiten[t];
            if(MyPanel.highG) tabmatrix[v]=PSaitenHighG[t];
        }
        
        for(int i=0;i<tabmatrix[0].length;i++)
        {
        	if(tabmatrix.length>=2) 
        	{
        		for(int j=0;j<tabmatrix[1].length;j++)
                {
        			if(tabmatrix.length>=3) 
                	{
        				for(int k=0;k<tabmatrix[2].length;k++)
                        {
        					if(tabmatrix.length>=4) 
                        	{
        						for(int l=0;l<tabmatrix[3].length;l++)
                                {
                                    if((i!=j) && (j!=k) && (k!=i) && (l!=k) && (l!=j) && (l!=i))
                                    {
                                        if((tabmatrix[0][i]!=-1) && (tabmatrix[1][j]!=-1) && (tabmatrix[2][k]!=-1)&& (tabmatrix[3][l]!=-1))
                                        {
                                            panel_text_all_tabs(new int[] {i,j,k,l});
                                            append_string(new int[] {i,j,k,l});
                                        }
                                    }
                                } 	
                        	}
        					
        					else
        					{
        						if((i!=j) && (tabmatrix[0][i]!=-1) && (tabmatrix[1][j]!=-1) && (tabmatrix[2][k]!=-1) && (i!=k) && (k!=j))
                                { 
                                    panel_text_all_tabs(new int[] {i,j,k});
                                    append_string(new int[] {i,j,k});
                                }
        					}
                        }
                	}
        			
        			else
        			{
        				if((i!=j) && (tabmatrix[0][i]!=-1) && (tabmatrix[1][j]!=-1) )
        				{
        					panel_text_all_tabs(new int[] {i,j});
        					append_string(new int[]{i,j});
        				}    
        			}        			
                }
        	}
        	
        	else
        	{
        		if(tabmatrix[0][i]!=-1)
                {
        		    panel_text_all_tabs(new int[] {i});
                    append_string(new int[] {i});
                }
        	}
        }    
    }
    
    public static void panel_text_all_tabs(int[] j) {
    	String[] str = new String[j.length] ;
    	for (int i = 0; i<j.length; i++) {
    		str[i] = "";
    		if (tabmatrix[i][j[i]] < 10) str[i] += "  ";
    		MyPanel.alltabs[j[i]] += str[i]+String.valueOf(tabmatrix[i][j[i]]) +" ";
    	}
   
    }
    
    public static void append_string(int[] i) {
    	for(int t=0;t<4;t++) {
    		int check = t;
    		if (!IntStream.of(i).anyMatch(x -> x == check)) {
                MyPanel.alltabs[t] += "     ";
    		}
        }
    }
    
    public static void initialize_notes(ArrayList notes) {
    	numberofnotes=0;
        for (int i=0;i<MyPanel.P.length;i++)
        {
            if (MyPanel.P[i]==true) {
                numberofnotes++;
                notes.add(i);
            }
        }
    }

    public static void find_best_tab()
    {
        ArrayList notes = new ArrayList();
        List<MyArray> paths = new ArrayList<MyArray>();
        initialize_notes(notes);
        int tab = 0;
       
        tabmatrix = new int[notes.size()][4];

        for(int v=0;v<notes.size();v++)
        {
            int t= (int) notes.get(v);
            if(MyPanel.lowG) tabmatrix[v]=PSaiten[t];
            if(MyPanel.highG) tabmatrix[v]=PSaitenHighG[t];
        }

        if(tabmatrix.length==1) 
        {
            for(int j=0;j<tabmatrix[0].length;j++)
            {
                if(tabmatrix[0][j]!=-1) 
                {
                    MyPanel.tab[j]=tabmatrix[0][j];
                	return;
                }
            }
        }

        if(tabmatrix.length==2)
        {
            for(int i=0;i<tabmatrix[0].length;i++)
            {
                for(int j=0;j<tabmatrix[1].length;j++)
                {
                    if((i!=j) && (tabmatrix[0][i]!=-1) && (tabmatrix[1][j]!=-1) )
                    {
                        paths.add(new MyArray(tabmatrix[0][i],tabmatrix[1][j],-1,-1));
                    }
                }
            }
        }

        if(tabmatrix.length==3)
        {
            for(int i=0;i<tabmatrix[0].length;i++)
            {
                for(int j=0;j<tabmatrix[1].length;j++)
                {
                    for(int k=0;k<tabmatrix[2].length;k++)
                    {
                        if((i!=j) && (tabmatrix[0][i]!=-1) && (tabmatrix[1][j]!=-1) && (tabmatrix[2][k]!=-1) && (i!=k) && (k!=j))
                        {
                            paths.add(new MyArray(tabmatrix[0][i],tabmatrix[1][j],tabmatrix[2][k],-1));
                        }
                    }
                }
            }
        }

        if(tabmatrix.length==4)
        {
            for(int i=0;i<tabmatrix[0].length;i++)
            {
                for(int j=0;j<tabmatrix[1].length;j++)
                {
                    for(int k=0;k<tabmatrix[2].length;k++)
                    {
                        for(int l=0;l<tabmatrix[3].length;l++)
                        {
                            if((i!=j) && (j!=k) && (k!=i) && (l!=k) && (l!=j) && (l!=i))
                            {
                                if((tabmatrix[0][i]!=-1) && (tabmatrix[1][j]!=-1) && (tabmatrix[2][k]!=-1)&& (tabmatrix[3][l]!=-1))
                                {
                                    paths.add(new MyArray(tabmatrix[0][i],tabmatrix[1][j],tabmatrix[2][k],tabmatrix[3][l]));
                                }
                            }
                        }
                    }
                }
            }
        }   
        
        tab = find_shortest_tab(paths);
        if(tab == -1) append_error_string();
        if(tab > -1) panel_text_best_tab(paths.get(tab).getItems(), tabmatrix.length);        
    }
    
    public static int find_shortest_tab(List<MyArray> paths) {
    	int delta = -1;
        int tab = -1;
        for(int i=0; i<paths.size();i++)
        {
            int[] n = paths.get(i).getItems();
            int min = n[0];
            int max = n[0];

            for(int j=1;j<4;j++)
            {
                if (n[j]< min) min = n[j];
                if (n[j]> max) max = n[j];
            }

            int d = max-min;
            if((i==0) || (d<delta)){
                delta = d;
                tab = i;
            }
        }
        
        return tab;
    }
    
    public static void panel_text_best_tab(int[] items, int n) {
    	int[] w = new int[]{-1,-1,-1,-1};
    	w = items;

        for(int j=0;j<n;j++)
        {
            for(int i=0;i<tabmatrix[0].length;i++)
            {
                if(tabmatrix[j][i]== w[j]) MyPanel.tab[i]= w[j];
            }
        }
    }
    
    public static void append_error_string() {
    	for(int i=0;i<tabmatrix[0].length;i++) MyPanel.tab[i]= -1;
        MyPanel.error = true;
        String str = "--x----x----x----x--";
        appendStrToFile("data.txt", str);
    }



    public static void appendStrToFile(String fileName, String str)
    {
        try
        {
            BufferedWriter out = new BufferedWriter( new FileWriter(fileName, true));
            out.write(str);
            out.newLine();
            out.close();
        }

        catch (IOException e)
        {
            System.out.println("exception occoured" + e);
        }
    }

    public static void process_xmlfile()
    {
        File file = new File("pitch.txt");
        file.deleteOnExit();
        BufferedReader countlines;
        String s;

        try
        {
            countlines = new BufferedReader(new FileReader(file));

            while ((s = countlines.readLine()) != null)
            {
                StringTokenizer st = new StringTokenizer(s);
                while(st.hasMoreTokens())
                {
                    String str2 = st.nextToken();

                    if(str2.compareTo("barline")==0) bar2= true;
                    if((str2.compareTo("barline")!=0) && (hash.get(str2) != null)) MyPanel.P[hash.get(str2)] = true;
                }

                find_best_tab();
                save_tab();
            }

            countlines.close();
            makeTABfile.doClick();
        }

        catch (FileNotFoundException e2) {e2.printStackTrace();}
        catch (IOException e) {e.printStackTrace();}
    }

    
    public static void set_N_S_B_false()
    {
        for(int i=0;i<MyPanel.S.length;i++) MyPanel.S[i]=false;
        for(int i=0;i<MyPanel.B.length;i++) MyPanel.B[i]=false;
        for(int i=0;i<MyPanel.N.length;i++) MyPanel.N[i]=false;
    }

    private static void unzip(String zipFilePath, String destDir) throws IOException
    {
        File dir = new File(destDir);
        if(!dir.exists()) dir.mkdirs();

        FileInputStream fis;
        byte[] buffer = new byte[1024];

        fis = new FileInputStream(zipFilePath);
        ZipInputStream zis = new ZipInputStream(fis);
        ZipEntry ze = zis.getNextEntry();
        while(ze != null)
        {
            String fileName = ze.getName();
            File newFile = new File(destDir + File.separator + fileName);
            new File(newFile.getParent()).mkdirs();
            FileOutputStream fos = new FileOutputStream(newFile);
            int len;

            while ((len = zis.read(buffer)) > 0)
            {
                fos.write(buffer, 0, len);
            }

            fos.close();
            zis.closeEntry();
            ze = zis.getNextEntry();
        }

        zis.closeEntry();
        zis.close();
        fis.close();
    }

    public static void main(String[] args)
    {
        fill_hash_map();
        fill_hashtab();

        try {
            imageuke = ImageIO.read(tabcreator.class.getResource("/resources/uke.png"));
            imageclef = ImageIO.read(tabcreator.class.getResource("/resources/clef.png"));
            BufferedWriter out = new BufferedWriter(new FileWriter(fileName));
            out.close();

        }

        catch (IOException e) {
            System.out.println("Exception Occurred" + e);
        }

        myframe = new JFrame("tabcreator");
        myframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myframe.getContentPane().setLayout(new BoxLayout(myframe.getContentPane(),BoxLayout.Y_AXIS));
        buttonlowG = new JButton("Low G");
        buttonhighG = new JButton("High G");
        buttonselectmusicXML = new JButton("select musicXML File");
        panel = new MyPanel();
        panel.setPreferredSize(new Dimension(1000,500));
        panel.setBackground(/*Color.DARK_GRAY*/Color.WHITE);
        filechooser = new JFileChooser();


        buttonselectmusicXML.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent arg0)
            {
                int returnVal = filechooser.showOpenDialog(myframe);

                if (returnVal == JFileChooser.APPROVE_OPTION)
                {
                    xmlfile = filechooser.getSelectedFile();

                    //if file ends with .mxl unzip fist
                    String name = xmlfile.getName();
                    int length = name.length();
                    if(name.substring(length-3, length).compareTo("mxl")==0)
                    {
                        String zipFilePath = xmlfile.getAbsolutePath();
                        String destDir = "output/";

                        try
                        {
                            unzip(zipFilePath, destDir);
                            File folder = new File("output/");
                            xmlfile = folder.listFiles()[0];
                            XML_parser parser = new XML_parser();
                            parser.parse(xmlfile);
                            process_xmlfile();
                        }

                        catch (IOException e) {e.printStackTrace();}
                    }

                    else
                    {
                    	XML_parser parser = new XML_parser();
                        parser.parse(xmlfile);
                        process_xmlfile();
                    }
                }
            }
        });

        buttonlowG.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent arg0)
            {
                MyPanel.lowG=true;
                MyPanel.highG=false;
                MyPanel.showtab = true;
                panel.repaint();
            }
        });

        buttonhighG.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent arg0)
            {
                MyPanel.highG=true;
                MyPanel.lowG=false;
                MyPanel.showtab = true;
                panel.repaint();
            }
        });

        panel.addMouseListener(new MouseAdapter()
        {
            public void mouseClicked(MouseEvent mouseEvent) {
                mousePT = mouseEvent.getPoint();

                if (mouseEvent.getClickCount() == 2) {
                    mbvalue = !mbvalue;
                    pm.setVisible(mbvalue);
                    Double x = mousePT.getX();
                    Double y = mousePT.getY();

                    pm.show(myframe.getContentPane(),x.intValue(), y.intValue());
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                mousePT = e.getPoint();

                if((mousePT.getX()>60+50) &&  (mousePT.getX()<90+50) && (mousePT.getY()>330) && (mousePT.getY()<360)){
                    MyPanel.N[1] = !MyPanel.N[1];
                    MyPanel.P[0] = MyPanel.N[1];
                }

                if((mousePT.getX()>90+50) &&  (mousePT.getX()<120+50) && (mousePT.getY()>315) && (mousePT.getY()<345)){
                    MyPanel.N[2] = !MyPanel.N[2];
                    MyPanel.P[2] = MyPanel.N[2];
                }

                if((mousePT.getX()>60+50) &&  (mousePT.getX()<90+50) && (mousePT.getY()>300) && (mousePT.getY()<330)){
                    MyPanel.N[3] = !MyPanel.N[3];
                    MyPanel.P[4] = MyPanel.N[3];
                }

                if((mousePT.getX()>90+50) &&  (mousePT.getX()<120+50) && (mousePT.getY()>285) && (mousePT.getY()<315)){
                    MyPanel.N[4] = !MyPanel.N[4];
                    MyPanel.P[5] = MyPanel.N[4];
                }

                if((mousePT.getX()>60+50) &&  (mousePT.getX()<90+50) && (mousePT.getY()>270) && (mousePT.getY()<300)) {
                    MyPanel.N[5] = !MyPanel.N[5];
                    MyPanel.P[7] = MyPanel.N[5];
                }

                if((mousePT.getX()>90+50) &&  (mousePT.getX()<120+50) && (mousePT.getY()>255) && (mousePT.getY()<285)){
                    MyPanel.N[6] = !MyPanel.N[6];
                    MyPanel.P[9] = MyPanel.N[6];
                }

                if((mousePT.getX()>60+50) &&  (mousePT.getX()<90+50) && (mousePT.getY()>240) && (mousePT.getY()<270)){
                    MyPanel.N[7] = !MyPanel.N[7];
                    MyPanel.P[10] = MyPanel.N[7];
                }

                if((mousePT.getX()>90+50) &&  (mousePT.getX()<120+50) && (mousePT.getY()>225) && (mousePT.getY()<255)){
                    MyPanel.N[8] = !MyPanel.N[8];
                    MyPanel.P[12] = MyPanel.N[8];
                }

                if((mousePT.getX()>60+50) &&  (mousePT.getX()<90+50) && (mousePT.getY()>210) && (mousePT.getY()<240)){
                    MyPanel.N[9] = !MyPanel.N[9];
                    MyPanel.P[14] = MyPanel.N[9];
                }

                if((mousePT.getX()>90+50) &&  (mousePT.getX()<120+50) && (mousePT.getY()>195) && (mousePT.getY()<225)){
                    MyPanel.N[10] = !MyPanel.N[10];
                    MyPanel.P[16] = MyPanel.N[10];
                }

                if((mousePT.getX()>60+50) &&  (mousePT.getX()<90+50) && (mousePT.getY()>180) && (mousePT.getY()<210)){
                    MyPanel.N[11] = !MyPanel.N[11];
                    MyPanel.P[17] = MyPanel.N[11];
                }

                if((mousePT.getX()>90+50) &&  (mousePT.getX()<120+50) && (mousePT.getY()>165) && (mousePT.getY()<195)){
                    MyPanel.N[12] = !MyPanel.N[12];
                    MyPanel.P[19] = MyPanel.N[12];
                }

                if((mousePT.getX()>60+50) &&  (mousePT.getX()<90+50) && (mousePT.getY()>150) && (mousePT.getY()<180)){
                    MyPanel.N[13] = !MyPanel.N[13];
                    MyPanel.P[21] = MyPanel.N[13];
                }

                if((mousePT.getX()>90+50) &&  (mousePT.getX()<120+50) && (mousePT.getY()>135) && (mousePT.getY()<165)){
                    MyPanel.N[14] = !MyPanel.N[14];
                    MyPanel.P[22] = MyPanel.N[14];
                }

                if((mousePT.getX()>60+50) &&  (mousePT.getX()<90+50) && (mousePT.getY()>120) && (mousePT.getY()<150)){
                    MyPanel.N[15] = !MyPanel.N[15];
                    MyPanel.P[24] = MyPanel.N[15];
                }

                if((mousePT.getX()>90+50) &&  (mousePT.getX()<120+50) && (mousePT.getY()>105) && (mousePT.getY()<135)){
                    MyPanel.N[16] = !MyPanel.N[16];
                    MyPanel.P[26] = MyPanel.N[16];
                }

                if((mousePT.getX()>60+50) &&  (mousePT.getX()<90+50) && (mousePT.getY()>90) && (mousePT.getY()<120)) {
                    MyPanel.N[17] = !MyPanel.N[17];
                    MyPanel.P[28] = MyPanel.N[17];
                }

                if((mousePT.getX()>90+50) &&  (mousePT.getX()<120+50) && (mousePT.getY()>75) && (mousePT.getY()<105)) {
                    MyPanel.N[18] = !MyPanel.N[18];
                    MyPanel.P[29] = MyPanel.N[18];
                }

                //# und b markieren
                if((mousePT.getX()>35+50) &&  (mousePT.getX()<65+50) && (mousePT.getY()>330) && (mousePT.getY()<360)) {
                    MyPanel.S[1] = !MyPanel.S[1];
                    MyPanel.N[1] = MyPanel.S[1];
                    MyPanel.P[1] = MyPanel.S[1];
                }

                if((mousePT.getX()>35+50) &&  (mousePT.getX()<65+50) && (mousePT.getY()>300) && (mousePT.getY()<330)){
                    MyPanel.S[3] = !MyPanel.S[3];
                    MyPanel.N[3] = MyPanel.S[3];
                    MyPanel.P[5] = MyPanel.S[3];
                    MyPanel.B[3] = false;
                }

                if((mousePT.getX()>35+50) &&  (mousePT.getX()<65+50) && (mousePT.getY()>270) && (mousePT.getY()<300)){
                    MyPanel.S[5] = !MyPanel.S[5];
                    MyPanel.N[5] = MyPanel.S[5];
                    MyPanel.P[8] = MyPanel.S[5];
                    MyPanel.B[5] = false;
                }

                if((mousePT.getX()>35+50) &&  (mousePT.getX()<65+50) && (mousePT.getY()>240) && (mousePT.getY()<270)){
                    MyPanel.S[7] = !MyPanel.S[7];
                    MyPanel.N[7] = MyPanel.S[7];
                    MyPanel.P[11] = MyPanel.S[7];
                    MyPanel.B[7] = false;
                }

                if((mousePT.getX()>35+50) &&  (mousePT.getX()<65+50) && (mousePT.getY()>210) && (mousePT.getY()<240)){
                    MyPanel.S[9] = !MyPanel.S[9];
                    MyPanel.N[9] = MyPanel.S[9];
                    MyPanel.P[15] = MyPanel.S[9];
                    MyPanel.B[9] = false;
                }

                if((mousePT.getX()>35+50) &&  (mousePT.getX()<65+50) && (mousePT.getY()>180) && (mousePT.getY()<210)){
                    MyPanel.S[11] = !MyPanel.S[11];
                    MyPanel.N[11] = MyPanel.S[11];
                    MyPanel.P[18] = MyPanel.S[11];
                    MyPanel.B[11] = false;
                }

                if((mousePT.getX()>35+50) &&  (mousePT.getX()<65+50) && (mousePT.getY()>150) && (mousePT.getY()<180)){
                    MyPanel.S[13] = !MyPanel.S[13];
                    MyPanel.N[13] = MyPanel.S[13];
                    MyPanel.P[22] = MyPanel.S[13];
                    MyPanel.B[13] = false;
                }

                if((mousePT.getX()>35+50) &&  (mousePT.getX()<65+50) && (mousePT.getY()>120) && (mousePT.getY()<150)){
                    MyPanel.S[15] = !MyPanel.S[15];
                    MyPanel.N[15] = MyPanel.S[15];
                    MyPanel.P[25] = MyPanel.S[15];
                    MyPanel.B[15] = false;
                }

                if((mousePT.getX()>35+50) &&  (mousePT.getX()<65+50) && (mousePT.getY()>90) && (mousePT.getY()<120)){
                    MyPanel.S[17] = !MyPanel.S[17];
                    MyPanel.N[17]= MyPanel.S[17];
                    MyPanel.P[29] = MyPanel.S[17];
                    MyPanel.B[17] = false;
                }

                if((mousePT.getX()>115+50) &&  (mousePT.getX()<145+50) && (mousePT.getY()>315) && (mousePT.getY()<345)){
                    MyPanel.S[2] = !MyPanel.S[2];
                    MyPanel.N[2] = MyPanel.S[2];
                    MyPanel.P[3] = MyPanel.S[2];
                    MyPanel.B[2] = false;
                }

                if((mousePT.getX()>115+50) &&  (mousePT.getX()<145+50) && (mousePT.getY()>285) && (mousePT.getY()<315)){
                    MyPanel.S[4] = !MyPanel.S[4];
                    MyPanel.N[4] = MyPanel.S[4];
                    MyPanel.P[6] = MyPanel.S[4];
                    MyPanel.B[4] = false;
                }

                if((mousePT.getX()>115+50) &&  (mousePT.getX()<145+50) && (mousePT.getY()>255) && (mousePT.getY()<285)){
                    MyPanel.S[6] = !MyPanel.S[6];
                    MyPanel.N[6] = MyPanel.S[6];
                    MyPanel.P[10] = MyPanel.S[6];
                    MyPanel.B[6] = false;
                }

                if((mousePT.getX()>115+50) &&  (mousePT.getX()<145+50) && (mousePT.getY()>225) && (mousePT.getY()<255)){
                    MyPanel.S[8] = !MyPanel.S[8];
                    MyPanel.N[8] = MyPanel.S[8];
                    MyPanel.P[13] = MyPanel.S[8];
                    MyPanel.B[8] = false;
                }

                if((mousePT.getX()>115+50) &&  (mousePT.getX()<145+50) && (mousePT.getY()>185) && (mousePT.getY()<225)){
                    MyPanel.S[10] = !MyPanel.S[10];
                    MyPanel.N[10] = MyPanel.S[10];
                    MyPanel.P[17]= MyPanel.S[10];
                    MyPanel.B[10] = false;
                }

                if((mousePT.getX()>115+50) &&  (mousePT.getX()<145+50) && (mousePT.getY()>155) && (mousePT.getY()<185)){
                    MyPanel.S[12] = !MyPanel.S[12];
                    MyPanel.N[12] = MyPanel.S[12];
                    MyPanel.P[20] = MyPanel.S[12];
                    MyPanel.B[12] = false;
                }

                if((mousePT.getX()>115+50) &&  (mousePT.getX()<145+50) && (mousePT.getY()>125) && (mousePT.getY()<155)){
                    MyPanel.S[14] = !MyPanel.S[14];
                    MyPanel.N[14] = MyPanel.S[14];
                    MyPanel.P[23] =MyPanel.S[14];
                    MyPanel.B[14] = false;
                }

                if((mousePT.getX()>115+50) &&  (mousePT.getX()<145+50) && (mousePT.getY()>95) && (mousePT.getY()<125)){
                    MyPanel.S[16] = !MyPanel.S[16];
                    MyPanel.N[16] = MyPanel.S[16];
                    MyPanel.P[27] = MyPanel.S[16];
                    MyPanel.B[16] = false;
                }

                if((mousePT.getX()>10+50) &&  (mousePT.getX()<30+50) && (mousePT.getY()>300) && (mousePT.getY()<330)){
                    MyPanel.B[3] = !MyPanel.B[3];
                    MyPanel.N[3] = MyPanel.B[3];
                    MyPanel.P[3] = MyPanel.B[3];
                    MyPanel.S[3] = false;
                }

                if((mousePT.getX()>10+50) &&  (mousePT.getX()<30+50) && (mousePT.getY()>270) && (mousePT.getY()<300)){
                    MyPanel.B[5] = !MyPanel.B[5];
                    MyPanel.N[5] = MyPanel.B[5];
                    MyPanel.P[6] = MyPanel.B[5];
                    MyPanel.S[5] = false;
                }

                if((mousePT.getX()>10+50) &&  (mousePT.getX()<30+50) && (mousePT.getY()>240) && (mousePT.getY()<270)){
                    MyPanel.B[7] = !MyPanel.B[7];
                    MyPanel.N[7] = MyPanel.B[7];
                    MyPanel.P[9] = MyPanel.B[7];
                    MyPanel.S[7] = false;
                }

                if((mousePT.getX()>10+50) &&  (mousePT.getX()<30+50) && (mousePT.getY()>210) && (mousePT.getY()<240)){
                    MyPanel.B[9] = !MyPanel.B[9];
                    MyPanel.N[9] = MyPanel.B[9];
                    MyPanel.P[13] = MyPanel.B[9];
                    MyPanel.S[9] = false;
                }

                if((mousePT.getX()>10+50) &&  (mousePT.getX()<30+50) && (mousePT.getY()>180) && (mousePT.getY()<210)){
                    MyPanel.B[11] = !MyPanel.B[11];
                    MyPanel.N[11] = MyPanel.B[11];
                    MyPanel.P[16] = MyPanel.B[11];
                    MyPanel.S[11] = false;
                }

                if((mousePT.getX()>10+50) &&  (mousePT.getX()<30+50) && (mousePT.getY()>150) && (mousePT.getY()<180)){
                    MyPanel.B[13] = !MyPanel.B[13];
                    MyPanel.N[13] = MyPanel.B[13];
                    MyPanel.P[20] = MyPanel.B[13];
                    MyPanel.S[13] = false;
                }

                if((mousePT.getX()>10+50) &&  (mousePT.getX()<30+50) && (mousePT.getY()>120) && (mousePT.getY()<150)){
                    MyPanel.B[15] = !MyPanel.B[15];
                    MyPanel.N[15] = MyPanel.B[15];
                    MyPanel.P[23] = MyPanel.B[15];
                    MyPanel.S[15] = false;
                }

                if((mousePT.getX()>10+50) &&  (mousePT.getX()<30+50) && (mousePT.getY()>90) && (mousePT.getY()<120)){
                    MyPanel.B[17] = !MyPanel.B[17];
                    MyPanel.N[17] = MyPanel.B[17];
                    MyPanel.P[27] = MyPanel.B[17];
                    MyPanel.S[17] = false;
                }

                if((mousePT.getX()>145+50) &&  (mousePT.getX()<175+50) && (mousePT.getY()>315) && (mousePT.getY()<345)){
                    MyPanel.B[2] = !MyPanel.B[2];
                    MyPanel.N[2] = MyPanel.B[2];
                    MyPanel.P[1] = MyPanel.B[2];
                    MyPanel.S[2] = false;
                }

                if((mousePT.getX()>145+50) &&  (mousePT.getX()<175+50) && (mousePT.getY()>285) && (mousePT.getY()<315)){
                    MyPanel.B[4] = !MyPanel.B[4];
                    MyPanel.N[4] = MyPanel.B[4];
                    MyPanel.P[4] = MyPanel.B[4];
                    MyPanel.S[4] = false;
                }

                if((mousePT.getX()>145+50) &&  (mousePT.getX()<175+50) && (mousePT.getY()>255) && (mousePT.getY()<285)){
                    MyPanel.B[6] = !MyPanel.B[6];
                    MyPanel.N[6] = MyPanel.B[6];
                    MyPanel.P[8] = MyPanel.B[6];
                    MyPanel.S[6] = false;
                }

                if((mousePT.getX()>145+50) &&  (mousePT.getX()<175+50) && (mousePT.getY()>225) && (mousePT.getY()<255)){
                    MyPanel.B[8] = !MyPanel.B[8];
                    MyPanel.N[8] = MyPanel.B[8];
                    MyPanel.P[15] = MyPanel.B[8];
                    MyPanel.S[8] = false;
                }

                if((mousePT.getX()>145+50) &&  (mousePT.getX()<175+50) && (mousePT.getY()>185) && (mousePT.getY()<225))
                {
                    MyPanel.B[10] = !MyPanel.B[10];
                    MyPanel.N[10] = MyPanel.B[10];
                    MyPanel.P[15] = MyPanel.B[10];
                    MyPanel.S[10] = false;
                }

                if((mousePT.getX()>145+50) &&  (mousePT.getX()<175+50) && (mousePT.getY()>155) && (mousePT.getY()<185)){
                    MyPanel.B[12] = !MyPanel.B[12];
                    MyPanel.N[12] = MyPanel.B[12];
                    MyPanel.P[18] = MyPanel.B[12];
                    MyPanel.S[12] = false;
                }

                if((mousePT.getX()>145+50) &&  (mousePT.getX()<175+50) && (mousePT.getY()>125) && (mousePT.getY()<155)){
                    MyPanel.B[14] = !MyPanel.B[14];
                    MyPanel.N[14] = MyPanel.B[14];
                    MyPanel.P[21] = MyPanel.B[14];
                    MyPanel.S[14] = false;
                }

                if((mousePT.getX()>145+50) &&  (mousePT.getX()<175+50) && (mousePT.getY()>95) && (mousePT.getY()<125)){
                    MyPanel.B[16] = !MyPanel.B[16];
                    MyPanel.N[16] = MyPanel.B[16];
                    MyPanel.P[25] =MyPanel.B[16];
                    MyPanel.S[16]= false;
                }

                if((mousePT.getX()>145+50) &&  (mousePT.getX()<175+50) && (mousePT.getY()>65) && (mousePT.getY()<95)){
                    MyPanel.B[18] = !MyPanel.B[18];
                    MyPanel.N[18] = MyPanel.B[18];
                    MyPanel.P[28] = MyPanel.B[18];
                }

                panel.repaint();
            }
        });

        convertbutton= new JButton("Convert");
        clearbutton = new JButton("Clear");
        makeTABfile = new JButton("makeTABfile");
        barline = new JButton("Insert bar '|'");
        showAll = new JButton("Convert All");
        tabCombinations = new JButton("All Tab Combinations");


        tabCombinations.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent arg0)
            {
                if(MyPanel.lowG) {
                    if (tf1.getText().matches(".*\\d.*")){
                        int tab1 = Integer.parseInt(tf1.getText().trim());
                        switch (tab1) {
                            case 0: MyPanel.N[9] = true; MyPanel.B[9] = false; MyPanel.S[9]= false; MyPanel.P[14] = true; break;
                            case 1: MyPanel.N[9] = true; MyPanel.B[9] = false; MyPanel.S[9]= true; MyPanel.P[15] = true; break;
                            case 2: MyPanel.N[10] = true; MyPanel.B[10] = false; MyPanel.S[10]= false; MyPanel.P[16] = true; break;
                            case 3: MyPanel.N[11] = true; MyPanel.B[11] = false; MyPanel.S[11]= false; MyPanel.P[17] = true; break;
                            case 4: MyPanel.N[11] = true; MyPanel.B[11] = false; MyPanel.S[11]= true; MyPanel.P[18] = true; break;
                            case 5: MyPanel.N[12] = true; MyPanel.B[12] = false; MyPanel.S[12]= false; MyPanel.P[19] = true; break;
                            case 6: MyPanel.N[12] = true; MyPanel.B[12] = false; MyPanel.S[12]= true; MyPanel.P[20] = true; break;
                            case 7: MyPanel.N[13] = true; MyPanel.B[13] = false; MyPanel.S[13]= false; MyPanel.P[21] = true; break;
                            case 8: MyPanel.N[14] = true; MyPanel.B[14] = false; MyPanel.S[14]= false; MyPanel.P[22] = true; break;
                            case 9: MyPanel.N[14] = true; MyPanel.B[14] = false; MyPanel.S[14]= true; MyPanel.P[23] = true; break;
                            case 10: MyPanel.N[15] = true; MyPanel.B[15] = false; MyPanel.S[15]= false; MyPanel.P[24] = true; break;
                            case 11: MyPanel.N[15] = true; MyPanel.B[15] = false; MyPanel.S[15]= true; MyPanel.P[25] = true; break;
                            case 12: MyPanel.N[16] = true; MyPanel.B[16] = false; MyPanel.S[16]= false; MyPanel.P[26] = true; break;
                            case 13: MyPanel.N[16] = true; MyPanel.B[16] = false; MyPanel.S[16]= true; MyPanel.P[27] = true; break;
                            case 14: MyPanel.N[17] = true; MyPanel.B[17] = false; MyPanel.S[17] = false; MyPanel.P[28] = true; break;
                            case 15: MyPanel.N[18] = true; MyPanel.B[18] = false; break;
                        }
                    }

                    if(tf2.getText().matches(".*\\d.*")) {
                        int tab2 = Integer.parseInt(tf2.getText().trim());
                        switch (tab2) {
                            case 0: MyPanel.N[6] = true; MyPanel.B[6] = false; MyPanel.S[6]= false; MyPanel.P[9] = true; break;
                            case 1: MyPanel.N[7] = true; MyPanel.B[7] = false; MyPanel.S[7]= false; MyPanel.P[10] = true; break;
                            case 2: MyPanel.N[7] = true; MyPanel.B[7] = false; MyPanel.S[7]= true; MyPanel.P[11] = true; break;
                            case 3: MyPanel.N[8] = true; MyPanel.B[8] = false; MyPanel.S[8]= false; MyPanel.P[12] = true; break;
                            case 4: MyPanel.N[8] = true; MyPanel.B[8] = false; MyPanel.S[8]= true; MyPanel.P[13] = true; break;
                            case 5: MyPanel.N[9] = true; MyPanel.B[9] = false; MyPanel.S[9]= false; MyPanel.P[14] = true; break;
                            case 6: MyPanel.N[9] = true; MyPanel.B[9] = false; MyPanel.S[9]= true; MyPanel.P[15] = true; break;
                            case 7: MyPanel.N[10] = true; MyPanel.B[10] = false; MyPanel.S[10]= false; MyPanel.P[16] = true; break;
                            case 8: MyPanel.N[11] = true; MyPanel.B[11] = false; MyPanel.S[11]= false; MyPanel.P[17] = true; break;
                            case 9: MyPanel.N[11] = true; MyPanel.B[11] = false; MyPanel.S[11]= true; MyPanel.P[18] = true; break;
                            case 10: MyPanel.N[12] = true; MyPanel.B[12] = false; MyPanel.S[12]= false; MyPanel.P[19] = true; break;
                            case 11: MyPanel.N[12] = true; MyPanel.B[12] = false; MyPanel.S[12]= true; MyPanel.P[20] = true; break;
                            case 12: MyPanel.N[13] = true; MyPanel.B[13] = false; MyPanel.S[13]= false; MyPanel.P[21] = true; break;
                            case 13: MyPanel.N[14] = true; MyPanel.B[14] = false; MyPanel.S[14]= false; MyPanel.P[22] = true; break;
                            case 14: MyPanel.N[14] = true; MyPanel.B[14] = false; MyPanel.S[14] = true; MyPanel.P[23] = true; break;
                            case 15: MyPanel.N[15] = true; MyPanel.B[15] = false; MyPanel.S[15] = false; MyPanel.P[24] = true; break;
                        }
                    }

                    if(tf3.getText().matches(".*\\d.*")){
                        int tab3 = Integer.parseInt(tf3.getText().trim());
                        switch (tab3) {
                            case 0: MyPanel.N[4] = true; MyPanel.B[4] = false; MyPanel.S[4]= false; MyPanel.P[5] = true; break;
                            case 1: MyPanel.N[4] = true; MyPanel.B[4] = false; MyPanel.S[4]= true; MyPanel.P[6] = true; break;
                            case 2: MyPanel.N[5] = true; MyPanel.B[5] = false; MyPanel.S[5]= false; MyPanel.P[7] = true; break;
                            case 3: MyPanel.N[5] = true; MyPanel.B[5] = false; MyPanel.S[5]= true; MyPanel.P[8] = true; break;
                            case 4: MyPanel.N[6] = true; MyPanel.B[6] = false; MyPanel.S[6]= false; MyPanel.P[9] = true; break;
                            case 5: MyPanel.N[7] = true; MyPanel.B[7] = false; MyPanel.S[7]= false; MyPanel.P[10] = true; break;
                            case 6: MyPanel.N[7] = true; MyPanel.B[7] = false; MyPanel.S[7]= true; MyPanel.P[11] = true; break;
                            case 7: MyPanel.N[8] = true; MyPanel.B[8] = false; MyPanel.S[8]= false; MyPanel.P[12] = true; break;
                            case 8: MyPanel.N[8] = true; MyPanel.B[8] = false; MyPanel.S[8]= true; MyPanel.P[13] = true; break;
                            case 9: MyPanel.N[9] = true; MyPanel.B[9] = false; MyPanel.S[9]= false; MyPanel.P[14] = true; break;
                            case 10: MyPanel.N[9] = true; MyPanel.B[9] = false; MyPanel.S[9]= true; MyPanel.P[15] = true; break;
                            case 11: MyPanel.N[10] = true; MyPanel.B[10] = false; MyPanel.S[10]= false; MyPanel.P[16] = true; break;
                            case 12: MyPanel.N[11] = true; MyPanel.B[11] = false; MyPanel.S[11]= false; MyPanel.P[17] = true; break;
                            case 13: MyPanel.N[11] = true; MyPanel.B[11] = false; MyPanel.S[11]= true; MyPanel.P[18] = true; break;
                            case 14: MyPanel.N[12] = true; MyPanel.B[12] = false; MyPanel.S[12] = false; MyPanel.P[19] = true; break;
                            case 15: MyPanel.N[12] = true; MyPanel.B[12] = false; MyPanel.S[12] = true; MyPanel.P[20] = true; break;
                            case 16: MyPanel.N[13] = true; MyPanel.B[13] = false; MyPanel.S[12] = false; MyPanel.P[21] = true; break;
                            case 17: MyPanel.N[13] = true; MyPanel.B[13] = false; MyPanel.S[13] = true; MyPanel.P[22] = true; break;
                        }
                    }

                    if(tf4.getText().matches(".*\\d.*")){
                        int tab4 = Integer.parseInt(tf4.getText().trim());
                        switch (tab4) {
                            case 0: MyPanel.N[1] = true;  MyPanel.S[1]= false; MyPanel.P[0] = true; break;
                            case 1: MyPanel.N[1] = true;  MyPanel.S[1]= true; MyPanel.P[1] = true; break;
                            case 2: MyPanel.N[2] = true; MyPanel.B[2] = false; MyPanel.S[2]= false; MyPanel.P[2] = true; break;
                            case 3: MyPanel.N[2] = true; MyPanel.B[2] = false; MyPanel.S[2]= true; MyPanel.P[3] = true; break;
                            case 4: MyPanel.N[3] = true; MyPanel.B[3] = false; MyPanel.S[3]= false; MyPanel.P[4] = true; break;
                            case 5: MyPanel.N[4] = true; MyPanel.B[4] = false; MyPanel.S[4]= false; MyPanel.P[5] = true; break;
                            case 6: MyPanel.N[4] = true; MyPanel.B[4] = false; MyPanel.S[4]= true; MyPanel.P[6] = true; break;
                            case 7: MyPanel.N[5] = true; MyPanel.B[5] = false; MyPanel.S[5]= false; MyPanel.P[7] = true; break;
                            case 8: MyPanel.N[5] = true; MyPanel.B[5] = false; MyPanel.S[5]= true; MyPanel.P[8] = true; break;
                            case 9: MyPanel.N[6] = true; MyPanel.B[6] = false; MyPanel.S[6]= false; MyPanel.P[9] = true; break;
                            case 10: MyPanel.N[7] = true; MyPanel.B[7] = false; MyPanel.S[7]= false; MyPanel.P[10] = true; break;
                            case 11: MyPanel.N[7] = true; MyPanel.B[7] = false; MyPanel.S[7]= true; MyPanel.P[11] = true; break;
                            case 12: MyPanel.N[8] = true; MyPanel.B[8] = false; MyPanel.S[8]= false; MyPanel.P[12] = true; break;
                        }
                    }
                }

                if(MyPanel.highG) {
                    if (tf1.getText().matches(".*\\d.*")){
                        int tab1 = Integer.parseInt(tf1.getText().trim());
                        switch (tab1) {
                            case 0: MyPanel.N[9] = true; MyPanel.B[9] = false; MyPanel.S[9]= false; MyPanel.P[14] = true; break;
                            case 1: MyPanel.N[9] = true; MyPanel.B[9] = false; MyPanel.S[9]= true; MyPanel.P[15] = true; break;
                            case 2: MyPanel.N[10] = true; MyPanel.B[10] = false; MyPanel.S[10]= false; MyPanel.P[16] = true; break;
                            case 3: MyPanel.N[11] = true; MyPanel.B[11] = false; MyPanel.S[11]= false; MyPanel.P[17] = true; break;
                            case 4: MyPanel.N[11] = true; MyPanel.B[11] = false; MyPanel.S[11]= true; MyPanel.P[18] = true; break;
                            case 5: MyPanel.N[12] = true; MyPanel.B[12] = false; MyPanel.S[12]= false; MyPanel.P[19] = true; break;
                            case 6: MyPanel.N[12] = true; MyPanel.B[12] = false; MyPanel.S[12]= true; MyPanel.P[20] = true; break;
                            case 7: MyPanel.N[13] = true; MyPanel.B[13] = false; MyPanel.S[13]= false; MyPanel.P[21] = true; break;
                            case 8: MyPanel.N[14] = true; MyPanel.B[14] = false; MyPanel.S[14]= false; MyPanel.P[22] = true; break;
                            case 9: MyPanel.N[14] = true; MyPanel.B[14] = false; MyPanel.S[14]= true; MyPanel.P[23] = true; break;
                            case 10: MyPanel.N[15] = true; MyPanel.B[15] = false; MyPanel.S[15]= false; MyPanel.P[24] = true; break;
                            case 11: MyPanel.N[15] = true; MyPanel.B[15] = false; MyPanel.S[15]= true; MyPanel.P[25] = true; break;
                            case 12: MyPanel.N[16] = true; MyPanel.B[16] = false; MyPanel.S[16]= false; MyPanel.P[26] = true; break;
                            case 13: MyPanel.N[16] = true; MyPanel.B[16] = false; MyPanel.S[16]= true; MyPanel.P[27] = true; break;
                            case 14: MyPanel.N[17] = true; MyPanel.B[17] = false; MyPanel.S[17] = false; MyPanel.P[28] = true; break;
                            case 15: MyPanel.N[18] = true; MyPanel.B[18] = false; break;
                        }
                    }

                    if(tf2.getText().matches(".*\\d.*")) {
                        int tab2 = Integer.parseInt(tf2.getText().trim());
                        switch (tab2) {
                            case 0: MyPanel.N[6] = true; MyPanel.B[6] = false; MyPanel.S[6]= false; MyPanel.P[9] = true; break;
                            case 1: MyPanel.N[7] = true; MyPanel.B[7] = false; MyPanel.S[7]= false; MyPanel.P[10] = true; break;
                            case 2: MyPanel.N[7] = true; MyPanel.B[7] = false; MyPanel.S[7]= true; MyPanel.P[11] = true; break;
                            case 3: MyPanel.N[8] = true; MyPanel.B[8] = false; MyPanel.S[8]= false; MyPanel.P[12] = true; break;
                            case 4: MyPanel.N[8] = true; MyPanel.B[8] = false; MyPanel.S[8]= true; MyPanel.P[13] = true; break;
                            case 5: MyPanel.N[9] = true; MyPanel.B[9] = false; MyPanel.S[9]= false; MyPanel.P[14] = true; break;
                            case 6: MyPanel.N[9] = true; MyPanel.B[9] = false; MyPanel.S[9]= true; MyPanel.P[15] = true; break;
                            case 7: MyPanel.N[10] = true; MyPanel.B[10] = false; MyPanel.S[10]= false; MyPanel.P[16] = true; break;
                            case 8: MyPanel.N[11] = true; MyPanel.B[11] = false; MyPanel.S[11]= false; MyPanel.P[17] = true; break;
                            case 9: MyPanel.N[11] = true; MyPanel.B[11] = false; MyPanel.S[11]= true; MyPanel.P[18] = true; break;
                            case 10: MyPanel.N[12] = true; MyPanel.B[12] = false; MyPanel.S[12]= false; MyPanel.P[19] = true; break;
                            case 11: MyPanel.N[12] = true; MyPanel.B[12] = false; MyPanel.S[12]= true; MyPanel.P[20] = true; break;
                            case 12: MyPanel.N[13] = true; MyPanel.B[13] = false; MyPanel.S[13]= false; MyPanel.P[21] = true; break;
                            case 13: MyPanel.N[14] = true; MyPanel.B[14] = false; MyPanel.S[14]= false; MyPanel.P[22] = true; break;
                            case 14: MyPanel.N[14] = true; MyPanel.B[14] = false; MyPanel.S[14] = true; MyPanel.P[23] = true; break;
                            case 15: MyPanel.N[15] = true; MyPanel.B[15] = false; MyPanel.S[15] = false; MyPanel.P[24] = true; break;
                        }
                    }

                    if(tf3.getText().matches(".*\\d.*")){
                        int tab3 = Integer.parseInt(tf3.getText().trim());
                        switch (tab3) {
                            case 0: MyPanel.N[4] = true; MyPanel.B[4] = false; MyPanel.S[4]= false; MyPanel.P[5] = true; break;
                            case 1: MyPanel.N[4] = true; MyPanel.B[4] = false; MyPanel.S[4]= true; MyPanel.P[6] = true; break;
                            case 2: MyPanel.N[5] = true; MyPanel.B[5] = false; MyPanel.S[5]= false; MyPanel.P[7] = true; break;
                            case 3: MyPanel.N[5] = true; MyPanel.B[5] = false; MyPanel.S[5]= true; MyPanel.P[8] = true; break;
                            case 4: MyPanel.N[6] = true; MyPanel.B[6] = false; MyPanel.S[6]= false; MyPanel.P[9] = true; break;
                            case 5: MyPanel.N[7] = true; MyPanel.B[7] = false; MyPanel.S[7]= false; MyPanel.P[10] = true; break;
                            case 6: MyPanel.N[7] = true; MyPanel.B[7] = false; MyPanel.S[7]= true; MyPanel.P[11] = true; break;
                            case 7: MyPanel.N[8] = true; MyPanel.B[8] = false; MyPanel.S[8]= false; MyPanel.P[12] = true; break;
                            case 8: MyPanel.N[8] = true; MyPanel.B[8] = false; MyPanel.S[8]= true; MyPanel.P[13] = true; break;
                            case 9: MyPanel.N[9] = true; MyPanel.B[9] = false; MyPanel.S[9]= false; MyPanel.P[14] = true; break;
                            case 10: MyPanel.N[9] = true; MyPanel.B[9] = false; MyPanel.S[9]= true; MyPanel.P[15] = true; break;
                            case 11: MyPanel.N[10] = true; MyPanel.B[10] = false; MyPanel.S[10]= false; MyPanel.P[16] = true; break;
                            case 12: MyPanel.N[11] = true; MyPanel.B[11] = false; MyPanel.S[11]= false; MyPanel.P[17] = true; break;
                            case 13: MyPanel.N[11] = true; MyPanel.B[11] = false; MyPanel.S[11]= true; MyPanel.P[18] = true; break;
                            case 14: MyPanel.N[12] = true; MyPanel.B[12] = false; MyPanel.S[12] = false; MyPanel.P[19] = true; break;
                            case 15: MyPanel.N[12] = true; MyPanel.B[12] = false; MyPanel.S[12] = true; MyPanel.P[20] = true; break;
                            case 16: MyPanel.N[13] = true; MyPanel.B[13] = false; MyPanel.S[12] = false; MyPanel.P[21] = true; break;
                            case 17: MyPanel.N[13] = true; MyPanel.B[13] = false; MyPanel.S[13] = true; MyPanel.P[22] = true; break;
                        }
                    }

                    if(tf4.getText().matches(".*\\d.*")){
                        int tab4 = Integer.parseInt(tf4.getText().trim());
                        switch (tab4) {
                            case 0: MyPanel.N[8] = true;  MyPanel.B[8] = false; MyPanel.S[8]= false; MyPanel.P[12] = true; break;
                            case 1: MyPanel.N[8] = true;  MyPanel.B[8]= false; MyPanel.S[8] = true; MyPanel.P[13] = true; break;
                            case 2: MyPanel.N[9] = true; MyPanel.B[9] = false; MyPanel.S[9]= false; MyPanel.P[14] = true; break;
                            case 3: MyPanel.N[9] = true; MyPanel.B[9] = false; MyPanel.S[9]= true; MyPanel.P[15] = true; break;
                            case 4: MyPanel.N[10] = true; MyPanel.B[10] = false; MyPanel.S[10]= false; MyPanel.P[16] = true; break;
                            case 5: MyPanel.N[4] = true; MyPanel.B[4] = false; MyPanel.S[4]= false; MyPanel.P[5] = true; break;
                            case 6: MyPanel.N[4] = true; MyPanel.B[4] = false; MyPanel.S[4]= true; MyPanel.P[6] = true; break;
                            case 7: MyPanel.N[5] = true; MyPanel.B[5] = false; MyPanel.S[5]= false; MyPanel.P[7] = true; break;
                            case 8: MyPanel.N[5] = true; MyPanel.B[5] = false; MyPanel.S[5]= true; MyPanel.P[8] = true; break;
                            case 9: MyPanel.N[6] = true; MyPanel.B[6] = false; MyPanel.S[6]= false; MyPanel.P[9] = true; break;
                            case 10: MyPanel.N[7] = true; MyPanel.B[7] = false; MyPanel.S[7]= false; MyPanel.P[10] = true; break;
                            case 11: MyPanel.N[7] = true; MyPanel.B[7] = false; MyPanel.S[7]= true; MyPanel.P[11] = true; break;
                        }
                    }
                }

                showAll.doClick();
            }
        });

        clearbutton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent arg0)
            {
                save_tab();
                tf1.setText("    ");
                tf2.setText("    ");
                tf3.setText("    ");
                tf4.setText("    ");
                mbvalue = false;
                //mb.setVisible(false);
                pm.setVisible(false);
                panel.repaint();
            }
        });

        convertbutton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if((MyPanel.N[1])&&(MyPanel.S[1])) MyPanel.P[0]=false;
                if((MyPanel.N[2])&&((MyPanel.S[2]) || (MyPanel.B[2])))  MyPanel.P[2]=false;
                if((MyPanel.N[3])&&((MyPanel.S[3]) || (MyPanel.B[3]))) MyPanel.P[4]=false;
                if((MyPanel.N[4])&&((MyPanel.S[4]) || (MyPanel.B[4]))) MyPanel.P[5]=false;
                if((MyPanel.N[5])&&((MyPanel.S[5]) || (MyPanel.B[5]))) MyPanel.P[7]=false;
                if((MyPanel.N[6])&&((MyPanel.S[6]) || (MyPanel.B[6]))) MyPanel.P[9]=false;
                if((MyPanel.N[7])&&((MyPanel.S[7]) || (MyPanel.B[7]))) MyPanel.P[10]=false;
                if((MyPanel.N[8])&&((MyPanel.S[8]) || (MyPanel.B[8]))) MyPanel.P[12]=false;
                if((MyPanel.N[9])&&((MyPanel.S[9]) || (MyPanel.B[9]))) MyPanel.P[14]=false;
                if((MyPanel.N[10])&&((MyPanel.S[10]) || (MyPanel.B[10]))) MyPanel.P[16]=false;
                if((MyPanel.N[11])&&((MyPanel.S[11]) || (MyPanel.B[11]))) MyPanel.P[17]=false;
                if((MyPanel.N[12])&&((MyPanel.S[12]) || (MyPanel.B[12]))) MyPanel.P[19]=false;
                if((MyPanel.N[13])&&((MyPanel.S[13]) || (MyPanel.B[13]))) MyPanel.P[21]=false;
                if((MyPanel.N[14])&&((MyPanel.S[14]) || (MyPanel.B[14]))) MyPanel.P[22]=false;
                if((MyPanel.N[15])&&((MyPanel.S[15]) || (MyPanel.B[15])))  MyPanel.P[24]=false;
                if((MyPanel.N[16])&&((MyPanel.S[16]) || (MyPanel.B[16]))) MyPanel.P[26]=false;
                if((MyPanel.N[17])&&((MyPanel.S[17]) || (MyPanel.B[17]))) MyPanel.P[28]=false;
                if((MyPanel.N[18])&&(MyPanel.B[18])) MyPanel.P[29]=false;

                find_best_tab();
                find_all_tabs();
                MyPanel.showtab = true;
                panel.repaint();
            }
        });

        showAll.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if((MyPanel.N[1])&&(MyPanel.S[1])) MyPanel.P[0]=false;
                if((MyPanel.N[2])&&((MyPanel.S[2]) || (MyPanel.B[2])))  MyPanel.P[2]=false;
                if((MyPanel.N[3])&&((MyPanel.S[3]) || (MyPanel.B[3]))) MyPanel.P[4]=false;
                if((MyPanel.N[4])&&((MyPanel.S[4]) || (MyPanel.B[4]))) MyPanel.P[5]=false;
                if((MyPanel.N[5])&&((MyPanel.S[5]) || (MyPanel.B[5]))) MyPanel.P[7]=false;
                if((MyPanel.N[6])&&((MyPanel.S[6]) || (MyPanel.B[6]))) MyPanel.P[9]=false;
                if((MyPanel.N[7])&&((MyPanel.S[7]) || (MyPanel.B[7]))) MyPanel.P[10]=false;
                if((MyPanel.N[8])&&((MyPanel.S[8]) || (MyPanel.B[8]))) MyPanel.P[12]=false;
                if((MyPanel.N[9])&&((MyPanel.S[9]) || (MyPanel.B[9]))) MyPanel.P[14]=false;
                if((MyPanel.N[10])&&((MyPanel.S[10]) || (MyPanel.B[10]))) MyPanel.P[16]=false;
                if((MyPanel.N[11])&&((MyPanel.S[11]) || (MyPanel.B[11]))) MyPanel.P[17]=false;
                if((MyPanel.N[12])&&((MyPanel.S[12]) || (MyPanel.B[12]))) MyPanel.P[19]=false;
                if((MyPanel.N[13])&&((MyPanel.S[13]) || (MyPanel.B[13]))) MyPanel.P[21]=false;
                if((MyPanel.N[14])&&((MyPanel.S[14]) || (MyPanel.B[14]))) MyPanel.P[22]=false;
                if((MyPanel.N[15])&&((MyPanel.S[15]) || (MyPanel.B[15])))  MyPanel.P[24]=false;
                if((MyPanel.N[16])&&((MyPanel.S[16]) || (MyPanel.B[16]))) MyPanel.P[26]=false;
                if((MyPanel.N[17])&&((MyPanel.S[17]) || (MyPanel.B[17]))) MyPanel.P[28]=false;
                if((MyPanel.N[18])&&(MyPanel.B[18])) MyPanel.P[29]=false;

                find_all_tabs();
                MyPanel.showall = true;
                panel.repaint();
            }
        });

        barline.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent arg0)
            {
                bar2 = true;
                clearbutton.doClick();
            }
        });

        makeTABfile.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent arg0)
            {
                File file = new File("data.txt"); //file.deleteOnExit();
                File f = new File("TABS.txt");

                if(f.exists() && !f.isDirectory())
                {
                    f.delete();
                }

                File fileout = new File("TABS.txt");
                int lines = 0;
                BufferedReader countlines;
                String s;
                try
                {
                    countlines = new BufferedReader(new FileReader(file));
                    while ((s = countlines.readLine()) != null)
                    {
                        lines++;
                    }
                    countlines.close();
                }

                catch (FileNotFoundException e2) {e2.printStackTrace();}
                catch (IOException e) {e.printStackTrace();}

                BufferedReader br1;
                BufferedReader br2;
                BufferedReader br3;
                BufferedReader br4;

                try
                {
                    br4 = new BufferedReader(new FileReader(file));
                    br3 = new BufferedReader(new FileReader(file));
                    br2 = new BufferedReader(new FileReader(file));
                    br1 = new BufferedReader(new FileReader(file));
                    String st;

                    BufferedWriter out = new BufferedWriter(new FileWriter(fileout, true));

                    int length = 20;
                    int loop = 0;
                    int wieoft = (int) Math.ceil(lines/length);
                    boolean cont = true;

                    while(cont)
                    {
                        int i1=0;
                        int i2=0; int i3=0; int i4=0;
                        if(loop==wieoft) cont = false;
                        out.write("A||");

                        while (((st = br4.readLine()) != null) && (i4<length))
                        {
                            String string4 = st.substring(15,19);
                            out.write(string4);
                            i4++;
                        }

                        if(st!=null)
                        {
                            String string4 = st.substring(15,19);
                            out.write(string4);
                        }

                        out.newLine();
                        out.write("E||");

                        while (((st= br3.readLine()) != null) && (i3<length))
                        {
                            String string3 = st.substring(10,14);
                            out.write(string3);
                            i3++;
                        }

                        if(st!=null)
                        {
                            String string3 = st.substring(10,14);
                            out.write(string3);
                        }

                        out.newLine();
                        out.write("C||");

                        while (((st = br2.readLine()) != null) && (i2<length))
                        {
                            String string2 = st.substring(5,9);
                            out.write(string2);
                            i2++;
                        }

                        if(st!=null)
                        {
                            String string2 = st.substring(5,9);
                            out.write(string2);
                        }

                        out.newLine();
                        out.write("G||");

                        while (((st = br1.readLine()) != null) && (i1<length))
                        {
                            String string1 = st.substring(0,4);
                            out.write(string1);
                            i1++;
                        }

                        if(st!=null)
                        {
                            String string1 = st.substring(0,4);
                            out.write(string1);
                        }

                        out.newLine(); out.newLine(); out.newLine();out.newLine(); out.newLine();
                        loop++;
                    }

                    out.close();
                    br4.close();
                    br3.close();
                    br2.close();
                    br1.close();
                }

                catch (FileNotFoundException e1) {e1.printStackTrace();}
                catch (IOException e) {e.printStackTrace();}
                file.delete();
                JOptionPane.showInternalMessageDialog(panel, "TAB file written");
            }
        });

        buttonpanel2 = new JPanel();
        buttonpanel2.add(buttonhighG);
        buttonpanel2.add(buttonlowG);
        buttonpanel2.add(buttonselectmusicXML);

        buttonpanel2.add(convertbutton);
        buttonpanel2.add(showAll);
        buttonpanel2.add(tabCombinations);
        buttonpanel2.add(clearbutton);
        buttonpanel2.add(makeTABfile);
        buttonpanel2.add(barline);
        panel.add(buttonpanel2);

        pm = new JPopupMenu();

        l1 = new JLabel("String 1:");
        test1 = new JPanel();
        test1.add(l1);
        tf1 = new JTextField();
        tf1.setText("     ");
        test1.add(tf1);
        pm.add(test1);

        l2 = new JLabel("String 2:");
        test2 = new JPanel();
        test2.add(l2);
        tf2 = new JTextField();
        tf2.setText("     ");
        test2.add(tf2);
        pm.add(test2);

        l3 = new JLabel("String 3:");
        test3 = new JPanel();
        test3.add(l3);
        tf3 = new JTextField();
        tf3.setText("     ");
        test3.add(tf3);
        pm.add(test3);

        l4 = new JLabel("String 4:");
        test4 = new JPanel();
        test4.add(l4);
        tf4 = new JTextField();
        tf4.setText("     ");
        test4.add(tf4);
      
        pm.add(test4);
        panel.add(pm);
        pm.setVisible(false);
        panel.repaint();

        myframe.getContentPane().add(panel);
        myframe.pack();
        myframe.setVisible(true);
    }
}

