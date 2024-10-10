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
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;

/**
 * Class <code>tabcreator</code> is the Main class. It creates the GUI.
 * @author Christine Merkel
 *
 */
public class TabCreator {
	/** Frame of the GUI */
    public static JFrame frame;
    
    /** Mainpanel that contains all other panels */
    public static JPanel panel;
    
    /** Panel that contains all the buttons */
    public static JPanel button_panel;
    
    /** Selects a MusicXML file */
    public static JButton selectmusicXML;
    
    /** Selects Low G */
    public static JButton lowG;
    
    /** Selects High G */
    public static JButton highG; 
    
    /** Converts the input note to the best ukulele tab */
    public static JButton convert;
    
    /** Clears the input */ 
    public static JButton clear;
    
    /** Makes a ukulele tab file from the input notes */
    public static JButton makeTABfile; 
    
    /** Inserts a barline in the ukulele tab file */
    public static JButton barline; 
    
    /** Converts the input note to all possible ukulele tabs */
    public static JButton convert_all; 
    
    /** Converts ukulele tab to piano note */
    public static JButton convert_to_piano;
    
    /** Mouse position */
    public static Point mousePT;
    
    /** Bar is inserted in tab file or not */
    public static boolean insert_bar;
    
    /**  Filechooser to select musicxml file */
    public static JFileChooser filechooser;
    
    /** Maps ukulele fret numbers to strings needed for tabfiles */
    public static HashMap<Integer, String> hash_tab; 
    
    /** Ukulele Image */
    public static BufferedImage imageuke;
    
    /** Clef Image */
    public static BufferedImage imageclef;
    
    /** Textfield in popup menu to enter fret in String 1 */
    public static JTextField tf1;
    
    /** Textfield in popup menu to enter fret in String 2 */
    public static JTextField tf2;
    
    /** Textfield in popup menu to enter fret in String 3 */
    public static JTextField tf3;
    
    /** Textfield in popup menu to enter fret in String 4 */
    public static JTextField tf4;
    
    /** Popup menu bar to insert ukulele tabs */
    public static JPopupMenu pm;
    
    /** Popup menu is showing or not */
    public static boolean show_popup_menu;
    
    /** Label 1 for textfield 1 in the popup menu */
    public static JLabel l1;
    
    /** Label 2 for textfield 2 in the popup menu */
    public static JLabel l2;
    
    /** Label 3 for textfield 3 in the popup menu */
    public static JLabel l3;
    
    /** Label 4 for textfield 4 in the popup menu */
    public static JLabel l4;
    
    /** panel 1 for textfield 1 in the popup menu */
    public static JPanel p1;
    
    /** panel 2 for textfield 2 in the popup menu */
    public static JPanel p2;
    
    /** panel 3 for textfield 3 in the popup menu */
    public static JPanel p3;
    
    /** panel 4 for textfield 4 in the popup menu */
    public static JPanel p4;
 
    /** Music note scale of a low g ukulele. The numbers are the frets in a string. The strings
     *  are in in th eorder {4,3,2,1}. -1 means there is no fret for that tone. Most tones can be
     *  played by multiple strings.
     */
    public static int[][] LowG = new int[][]{
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

    /** Music note scale of a high g ukulele. The numbers are the frets in a string. The strings
     *  are in in th eorder {4,3,2,1}. -1 means there is no fret for that tone. Most tones can be
     *  played by multiple strings.
     */
    public static int[][] HighG = new int[][]{
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

    /** Initializes the HashMap hash_tab */
    public static void init_hashtab()
    {
    	hash_tab = new HashMap<Integer, String>();
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
    
    /** Initializes the HashMap hash */
    public static HashMap<String,Integer> init_hashmap()
    {
    	HashMap<String, Integer> hash = new HashMap<String, Integer>();
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
        
        return hash;
    }

    /**
     * Saves input tabs in a temporary file 
     */
    public static void save_tab()
    {
        if(!insert_bar)
            appendStrToFile("temp.txt",
                    hash_tab.get(GraphicsPanel.tab[0])+hash_tab.get(GraphicsPanel.tab[1])+hash_tab.get(GraphicsPanel.tab[2])+hash_tab.get(GraphicsPanel.tab[3]));
        if(insert_bar) appendStrToFile("temp.txt", "--|----|----|----|--");
        for(int i=0;i<GraphicsPanel.P.length;i++)GraphicsPanel.P[i] = false;
        for(int j=0;j<GraphicsPanel.tab.length;j++)GraphicsPanel.tab[j] = -1;
        for(int j=0;j<GraphicsPanel.allTabs.length;j++) GraphicsPanel.allTabs[j] = "";
        set_N_S_B_false();
        insert_bar=false;
    }

    /**
     * Finds all possible tabs for a piano note
     */
    public static void find_all_tabs() {
        ArrayList<Integer> notes = new ArrayList<>();
        initialize_notes(notes);

        if (notes.isEmpty()) return;

        int[][] tabmatrix = new int[notes.size()][4];

        // Initialize the tabmatrix based on lowG and highG
        for (int v = 0; v < notes.size(); v++) {
            int t = notes.get(v);
            if (GraphicsPanel.lowG) tabmatrix[v] = LowG[t];
            if (GraphicsPanel.highG) tabmatrix[v] = HighG[t];
        }

        // Start recursive generation of all tab combinations
        generateTabs(tabmatrix, new int[tabmatrix.length], 0);
    }

    // Recursive function to generate and validate combinations of tabs
    private static void generateTabs(int[][] tabmatrix, int[] indices, int level) {
        if (level == tabmatrix.length) {
            if (isValidCombination(indices, tabmatrix)) {
                panel_text_all_tabs(indices, tabmatrix);
                append_string(indices);
            }
            return;
        }

        for (int i = 0; i < tabmatrix[level].length; i++) {
            indices[level] = i;
            generateTabs(tabmatrix, indices, level + 1);
        }
    }

    // Helper method to validate if the indices combination is valid
    private static boolean isValidCombination(int[] indices, int[][] tabmatrix) {
        // Check if values in tabmatrix for the given indices are valid (-1 means invalid)
        for (int i = 0; i < indices.length; i++) {
            if (tabmatrix[i][indices[i]] == -1) return false;
        }

        // Check if all indices are distinct
        for (int i = 0; i < indices.length - 1; i++) {
            for (int j = i + 1; j < indices.length; j++) {
                if (indices[i] == indices[j]) return false;
            }
        }
        return true;
    }

    
    
    
    
    /**
     * Writes the tabs on the graphics panel.
     * 
     * @param j          the strings
     * @param tabmatrix  the frets
     */
    public static void panel_text_all_tabs(int[] j, int[][] tabmatrix) {
    	String[] str = new String[j.length] ;
    	for (int i = 0; i<j.length; i++) {
    		str[i] = "";
    		if (tabmatrix[i][j[i]] < 10) str[i] += "  ";
    		GraphicsPanel.allTabs[j[i]] += str[i]+String.valueOf(tabmatrix[i][j[i]]) +" ";
    	}
   
    }
    
    /**
     * Adjusts the length of the Strings that are displayed an the graphics panel.
     * 
     * @param i number of ukulele notes selected in the GUI
     */
    public static void append_string(int[] i) {
    	for(int t=0;t<4;t++) {
    		int check = t;
    		if (!IntStream.of(i).anyMatch(x -> x == check)) {
                GraphicsPanel.allTabs[t] += "     ";
    		}
        }
    }
    
    /**
     * Initializes ArrayList notes.
     * 
     * @param notes Every note in the ukulele scale is selected in the GUI or not
     */
    public static void initialize_notes(ArrayList notes) {
        for (int i=0;i<GraphicsPanel.P.length;i++)
        {
            if (GraphicsPanel.P[i]==true) {
                notes.add(i);
            }
        }
    }

    /**
     * Finds the best tab for a piano note.
     */
    
    public static void find_best_tab() {
        ArrayList notes = new ArrayList();
        List<tabs_Array> paths = new ArrayList<>();
        initialize_notes(notes);
        
        if (notes.size() == 0) return; // Exit early if no notes

        int[][] tabmatrix = new int[notes.size()][4];
        initialize_tabmatrix(notes, tabmatrix);

        // Handle single note case
        if (tabmatrix.length == 1) {
            set_single_tab(tabmatrix[0]);
            return;
        }

        // Handle multiple notes
        generate_possible_tabs(tabmatrix, paths);
        
        int tab = find_shortest_tab(paths);
        if (tab == -1) {
            append_error_string(tabmatrix);
        } else {
            panel_text_best_tab(paths.get(tab).getItems(), tabmatrix.length, tabmatrix);
        }
    }

    private static void initialize_tabmatrix(ArrayList notes, int[][] tabmatrix) {
        for (int v = 0; v < notes.size(); v++) {
            int t = (int) notes.get(v);
            if (GraphicsPanel.lowG) tabmatrix[v] = LowG[t];
            if (GraphicsPanel.highG) tabmatrix[v] = HighG[t];
        }
    }

    private static void set_single_tab(int[] tabrow) {
        for (int j = 0; j < tabrow.length; j++) {
            if (tabrow[j] != -1) {
                GraphicsPanel.tab[j] = tabrow[j];
                return;
            }
        }
    }

    private static void generate_possible_tabs(int[][] tabmatrix, List<tabs_Array> paths) {
        int numNotes = tabmatrix.length;
        
        // Generate all possible valid tab combinations based on matrix length
        switch (numNotes) {
            case 2:
                find_combinations(tabmatrix, paths, 2);
                break;
            case 3:
                find_combinations(tabmatrix, paths, 3);
                break;
            case 4:
                find_combinations(tabmatrix, paths, 4);
                break;
        }
    }

    private static void find_combinations(int[][] tabmatrix, List<tabs_Array> paths, int numNotes) {
        for (int i = 0; i < tabmatrix[0].length; i++) {
            for (int j = 0; j < tabmatrix[1].length; j++) {
                if (numNotes >= 3) {
                    for (int k = 0; k < tabmatrix[2].length; k++) {
                        if (numNotes == 4) {
                            for (int l = 0; l < tabmatrix[3].length; l++) {
                                if (is_valid_combination(new int[]{i, j, k, l}, tabmatrix)) {
                                    paths.add(new tabs_Array(tabmatrix[0][i], tabmatrix[1][j], tabmatrix[2][k], tabmatrix[3][l]));
                                }
                            }
                        } else if (is_valid_combination(new int[]{i, j, k}, tabmatrix)) {
                            paths.add(new tabs_Array(tabmatrix[0][i], tabmatrix[1][j], tabmatrix[2][k], -1));
                        }
                    }
                } else if (is_valid_combination(new int[]{i, j}, tabmatrix)) {
                    paths.add(new tabs_Array(tabmatrix[0][i], tabmatrix[1][j], -1, -1));
                }
            }
        }
    }

    private static boolean is_valid_combination(int[] indices, int[][] tabmatrix) {
        for (int i = 0; i < indices.length; i++) {
            if (tabmatrix[i][indices[i]] == -1) return false;
            for (int j = i + 1; j < indices.length; j++) {
                if (indices[i] == indices[j]) return false;
            }
        }
        return true;
    }

    
    
    
    /**
     * Finds the tab with the shortest distance on the fretboard.
     * 
     * @param paths  all tabs corresponding to a piano note
     * @return       the tab with the shortest distance
     */
    public static int find_shortest_tab(List<tabs_Array> paths) {
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
    
    /**
     * Writes the best tab found on the graphics panel.
     * 
     * @param besttab    contains the best tab as list of integers, i.e. for every string the selected fret 
     * @param n          the number of tones the tab consists of
     * @param tabmatrix  the frets
     */
    public static void panel_text_best_tab(int[] besttab, int n, int[][] tabmatrix) {
    	int[] w = new int[]{-1,-1,-1,-1};
    	w = besttab;

        for(int j=0;j<n;j++)
        {
            for(int i=0;i<tabmatrix[0].length;i++)
            {
                if(tabmatrix[j][i]== w[j]) GraphicsPanel.tab[i]= w[j];
            }
        }
    }
    
    /**
     * Appends "--x----x----x----x--" to the tabfile in case there is no tab found.
     * 
     * @param tabmatrix
     */
    public static void append_error_string(int[][] tabmatrix) {
    	for(int i=0;i<tabmatrix[0].length;i++) GraphicsPanel.tab[i]= -1;
        GraphicsPanel.error = true;
        String str = "--x----x----x----x--";
        appendStrToFile("temp.txt", str);
    }

 
    /**
     * Appends a string to a file.
     * 
     * @param fileName  name of the file
     * @param str       string to append
     */
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

    /**
     *  Reads an MusicXML file and creates a tab fule with corresponding ukulele tabs.
     */
    public static void process_xmlfile()
    {
    	HashMap<String, Integer> hash = new HashMap<String, Integer>();
    	hash = init_hashmap(); 
    	
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

                    if(str2.compareTo("barline")==0) insert_bar= true;
                    if((str2.compareTo("barline")!=0) && (hash.get(str2) != null)) GraphicsPanel.P[hash.get(str2)] = true;
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

    /**
     * Sets tab_Panel.S[], tab_Panel.B[], and  tab_Panel.N[] to false.    
     */
    public static void set_N_S_B_false()
    {
        for(int i=0;i<GraphicsPanel.S.length;i++) GraphicsPanel.S[i]=false;
        for(int i=0;i<GraphicsPanel.B.length;i++) GraphicsPanel.B[i]=false;
        for(int i=0;i<GraphicsPanel.N.length;i++) GraphicsPanel.N[i]=false;
    }

    /**
     * Unzips files.
     * 
     * @param zipFilePath  path of the file
     * @param destDir      destination path of the unzipped file
     * @throws IOException in case of error
     */
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

    /**
     * The application's main entry point. Defines the frame and all GUI components.
     */
    public static void main(String[] args)
    {
        init_hashmap();
        init_hashtab();

        try {
            imageuke = ImageIO.read(TabCreator.class.getResource("/resources/uke.png"));
            imageclef = ImageIO.read(TabCreator.class.getResource("/resources/clef.png"));
            BufferedWriter out = new BufferedWriter(new FileWriter("temp.txt"));
            out.close();

        }

        catch (IOException e) {
            System.out.println("Exception Occurred" + e);
        }

        frame = new JFrame("tabcreator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(),BoxLayout.Y_AXIS));
        lowG = new JButton("Low G");
        highG = new JButton("High G");
        selectmusicXML = new JButton("select musicXML File");
        panel = new GraphicsPanel();
        panel.setPreferredSize(new Dimension(1000,500));
        panel.setBackground(Color.WHITE);
        filechooser = new JFileChooser();


        selectmusicXML.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent arg0)
            {
                int returnVal = filechooser.showOpenDialog(frame);

                if (returnVal == JFileChooser.APPROVE_OPTION)
                {
                    File xmlfile = filechooser.getSelectedFile();

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
                            MusicxmlParser parser = new MusicxmlParser();
                            parser.parse(xmlfile);
                            process_xmlfile();
                        }

                        catch (IOException e) {e.printStackTrace();}
                    }

                    else
                    {
                    	MusicxmlParser parser = new MusicxmlParser();
                        parser.parse(xmlfile);
                        process_xmlfile();
                    }
                }
            }
        });

        lowG.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent arg0)
            {
                GraphicsPanel.lowG=true;
                GraphicsPanel.highG=false;
                GraphicsPanel.showBestTab = true;
                panel.repaint();
            }
        });

        highG.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent arg0)
            {
                GraphicsPanel.highG=true;
                GraphicsPanel.lowG=false;
                GraphicsPanel.showBestTab = true;
                panel.repaint();
            }
        });

        panel.addMouseListener(new MouseAdapter()
        {
            public void mouseClicked(MouseEvent mouseEvent) {
                mousePT = mouseEvent.getPoint();

                if (mouseEvent.getClickCount() == 2) {
                    show_popup_menu = !show_popup_menu;
                    pm.setVisible(show_popup_menu);
                    Double x = mousePT.getX();
                    Double y = mousePT.getY();

                    pm.show(frame.getContentPane(),x.intValue(), y.intValue());
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                mousePT = e.getPoint();

                if((mousePT.getX()>60+50) &&  (mousePT.getX()<90+50) && (mousePT.getY()>330) && (mousePT.getY()<360)){
                    GraphicsPanel.N[1] = !GraphicsPanel.N[1];
                    GraphicsPanel.P[0] = GraphicsPanel.N[1];
                }

                if((mousePT.getX()>90+50) &&  (mousePT.getX()<120+50) && (mousePT.getY()>315) && (mousePT.getY()<345)){
                    GraphicsPanel.N[2] = !GraphicsPanel.N[2];
                    GraphicsPanel.P[2] = GraphicsPanel.N[2];
                }

                if((mousePT.getX()>60+50) &&  (mousePT.getX()<90+50) && (mousePT.getY()>300) && (mousePT.getY()<330)){
                    GraphicsPanel.N[3] = !GraphicsPanel.N[3];
                    GraphicsPanel.P[4] = GraphicsPanel.N[3];
                }

                if((mousePT.getX()>90+50) &&  (mousePT.getX()<120+50) && (mousePT.getY()>285) && (mousePT.getY()<315)){
                    GraphicsPanel.N[4] = !GraphicsPanel.N[4];
                    GraphicsPanel.P[5] = GraphicsPanel.N[4];
                }

                if((mousePT.getX()>60+50) &&  (mousePT.getX()<90+50) && (mousePT.getY()>270) && (mousePT.getY()<300)) {
                    GraphicsPanel.N[5] = !GraphicsPanel.N[5];
                    GraphicsPanel.P[7] = GraphicsPanel.N[5];
                }

                if((mousePT.getX()>90+50) &&  (mousePT.getX()<120+50) && (mousePT.getY()>255) && (mousePT.getY()<285)){
                    GraphicsPanel.N[6] = !GraphicsPanel.N[6];
                    GraphicsPanel.P[9] = GraphicsPanel.N[6];
                }

                if((mousePT.getX()>60+50) &&  (mousePT.getX()<90+50) && (mousePT.getY()>240) && (mousePT.getY()<270)){
                    GraphicsPanel.N[7] = !GraphicsPanel.N[7];
                    GraphicsPanel.P[10] = GraphicsPanel.N[7];
                }

                if((mousePT.getX()>90+50) &&  (mousePT.getX()<120+50) && (mousePT.getY()>225) && (mousePT.getY()<255)){
                    GraphicsPanel.N[8] = !GraphicsPanel.N[8];
                    GraphicsPanel.P[12] = GraphicsPanel.N[8];
                }

                if((mousePT.getX()>60+50) &&  (mousePT.getX()<90+50) && (mousePT.getY()>210) && (mousePT.getY()<240)){
                    GraphicsPanel.N[9] = !GraphicsPanel.N[9];
                    GraphicsPanel.P[14] = GraphicsPanel.N[9];
                }

                if((mousePT.getX()>90+50) &&  (mousePT.getX()<120+50) && (mousePT.getY()>195) && (mousePT.getY()<225)){
                    GraphicsPanel.N[10] = !GraphicsPanel.N[10];
                    GraphicsPanel.P[16] = GraphicsPanel.N[10];
                }

                if((mousePT.getX()>60+50) &&  (mousePT.getX()<90+50) && (mousePT.getY()>180) && (mousePT.getY()<210)){
                    GraphicsPanel.N[11] = !GraphicsPanel.N[11];
                    GraphicsPanel.P[17] = GraphicsPanel.N[11];
                }

                if((mousePT.getX()>90+50) &&  (mousePT.getX()<120+50) && (mousePT.getY()>165) && (mousePT.getY()<195)){
                    GraphicsPanel.N[12] = !GraphicsPanel.N[12];
                    GraphicsPanel.P[19] = GraphicsPanel.N[12];
                }

                if((mousePT.getX()>60+50) &&  (mousePT.getX()<90+50) && (mousePT.getY()>150) && (mousePT.getY()<180)){
                    GraphicsPanel.N[13] = !GraphicsPanel.N[13];
                    GraphicsPanel.P[21] = GraphicsPanel.N[13];
                }

                if((mousePT.getX()>90+50) &&  (mousePT.getX()<120+50) && (mousePT.getY()>135) && (mousePT.getY()<165)){
                    GraphicsPanel.N[14] = !GraphicsPanel.N[14];
                    GraphicsPanel.P[22] = GraphicsPanel.N[14];
                }

                if((mousePT.getX()>60+50) &&  (mousePT.getX()<90+50) && (mousePT.getY()>120) && (mousePT.getY()<150)){
                    GraphicsPanel.N[15] = !GraphicsPanel.N[15];
                    GraphicsPanel.P[24] = GraphicsPanel.N[15];
                }

                if((mousePT.getX()>90+50) &&  (mousePT.getX()<120+50) && (mousePT.getY()>105) && (mousePT.getY()<135)){
                    GraphicsPanel.N[16] = !GraphicsPanel.N[16];
                    GraphicsPanel.P[26] = GraphicsPanel.N[16];
                }

                if((mousePT.getX()>60+50) &&  (mousePT.getX()<90+50) && (mousePT.getY()>90) && (mousePT.getY()<120)) {
                    GraphicsPanel.N[17] = !GraphicsPanel.N[17];
                    GraphicsPanel.P[28] = GraphicsPanel.N[17];
                }

                if((mousePT.getX()>90+50) &&  (mousePT.getX()<120+50) && (mousePT.getY()>75) && (mousePT.getY()<105)) {
                    GraphicsPanel.N[18] = !GraphicsPanel.N[18];
                    GraphicsPanel.P[29] = GraphicsPanel.N[18];
                }

                //mark # and b 
                if((mousePT.getX()>35+50) &&  (mousePT.getX()<65+50) && (mousePT.getY()>330) && (mousePT.getY()<360)) {
                    GraphicsPanel.S[1] = !GraphicsPanel.S[1];
                    GraphicsPanel.N[1] = GraphicsPanel.S[1];
                    GraphicsPanel.P[1] = GraphicsPanel.S[1];
                }

                if((mousePT.getX()>35+50) &&  (mousePT.getX()<65+50) && (mousePT.getY()>300) && (mousePT.getY()<330)){
                    GraphicsPanel.S[3] = !GraphicsPanel.S[3];
                    GraphicsPanel.N[3] = GraphicsPanel.S[3];
                    GraphicsPanel.P[5] = GraphicsPanel.S[3];
                    GraphicsPanel.B[3] = false;
                }

                if((mousePT.getX()>35+50) &&  (mousePT.getX()<65+50) && (mousePT.getY()>270) && (mousePT.getY()<300)){
                    GraphicsPanel.S[5] = !GraphicsPanel.S[5];
                    GraphicsPanel.N[5] = GraphicsPanel.S[5];
                    GraphicsPanel.P[8] = GraphicsPanel.S[5];
                    GraphicsPanel.B[5] = false;
                }

                if((mousePT.getX()>35+50) &&  (mousePT.getX()<65+50) && (mousePT.getY()>240) && (mousePT.getY()<270)){
                    GraphicsPanel.S[7] = !GraphicsPanel.S[7];
                    GraphicsPanel.N[7] = GraphicsPanel.S[7];
                    GraphicsPanel.P[11] = GraphicsPanel.S[7];
                    GraphicsPanel.B[7] = false;
                }

                if((mousePT.getX()>35+50) &&  (mousePT.getX()<65+50) && (mousePT.getY()>210) && (mousePT.getY()<240)){
                    GraphicsPanel.S[9] = !GraphicsPanel.S[9];
                    GraphicsPanel.N[9] = GraphicsPanel.S[9];
                    GraphicsPanel.P[15] = GraphicsPanel.S[9];
                    GraphicsPanel.B[9] = false;
                }

                if((mousePT.getX()>35+50) &&  (mousePT.getX()<65+50) && (mousePT.getY()>180) && (mousePT.getY()<210)){
                    GraphicsPanel.S[11] = !GraphicsPanel.S[11];
                    GraphicsPanel.N[11] = GraphicsPanel.S[11];
                    GraphicsPanel.P[18] = GraphicsPanel.S[11];
                    GraphicsPanel.B[11] = false;
                }

                if((mousePT.getX()>35+50) &&  (mousePT.getX()<65+50) && (mousePT.getY()>150) && (mousePT.getY()<180)){
                    GraphicsPanel.S[13] = !GraphicsPanel.S[13];
                    GraphicsPanel.N[13] = GraphicsPanel.S[13];
                    GraphicsPanel.P[22] = GraphicsPanel.S[13];
                    GraphicsPanel.B[13] = false;
                }

                if((mousePT.getX()>35+50) &&  (mousePT.getX()<65+50) && (mousePT.getY()>120) && (mousePT.getY()<150)){
                    GraphicsPanel.S[15] = !GraphicsPanel.S[15];
                    GraphicsPanel.N[15] = GraphicsPanel.S[15];
                    GraphicsPanel.P[25] = GraphicsPanel.S[15];
                    GraphicsPanel.B[15] = false;
                }

                if((mousePT.getX()>35+50) &&  (mousePT.getX()<65+50) && (mousePT.getY()>90) && (mousePT.getY()<120)){
                    GraphicsPanel.S[17] = !GraphicsPanel.S[17];
                    GraphicsPanel.N[17]= GraphicsPanel.S[17];
                    GraphicsPanel.P[29] = GraphicsPanel.S[17];
                    GraphicsPanel.B[17] = false;
                }

                if((mousePT.getX()>115+50) &&  (mousePT.getX()<145+50) && (mousePT.getY()>315) && (mousePT.getY()<345)){
                    GraphicsPanel.S[2] = !GraphicsPanel.S[2];
                    GraphicsPanel.N[2] = GraphicsPanel.S[2];
                    GraphicsPanel.P[3] = GraphicsPanel.S[2];
                    GraphicsPanel.B[2] = false;
                }

                if((mousePT.getX()>115+50) &&  (mousePT.getX()<145+50) && (mousePT.getY()>285) && (mousePT.getY()<315)){
                    GraphicsPanel.S[4] = !GraphicsPanel.S[4];
                    GraphicsPanel.N[4] = GraphicsPanel.S[4];
                    GraphicsPanel.P[6] = GraphicsPanel.S[4];
                    GraphicsPanel.B[4] = false;
                }

                if((mousePT.getX()>115+50) &&  (mousePT.getX()<145+50) && (mousePT.getY()>255) && (mousePT.getY()<285)){
                    GraphicsPanel.S[6] = !GraphicsPanel.S[6];
                    GraphicsPanel.N[6] = GraphicsPanel.S[6];
                    GraphicsPanel.P[10] = GraphicsPanel.S[6];
                    GraphicsPanel.B[6] = false;
                }

                if((mousePT.getX()>115+50) &&  (mousePT.getX()<145+50) && (mousePT.getY()>225) && (mousePT.getY()<255)){
                    GraphicsPanel.S[8] = !GraphicsPanel.S[8];
                    GraphicsPanel.N[8] = GraphicsPanel.S[8];
                    GraphicsPanel.P[13] = GraphicsPanel.S[8];
                    GraphicsPanel.B[8] = false;
                }

                if((mousePT.getX()>115+50) &&  (mousePT.getX()<145+50) && (mousePT.getY()>185) && (mousePT.getY()<225)){
                    GraphicsPanel.S[10] = !GraphicsPanel.S[10];
                    GraphicsPanel.N[10] = GraphicsPanel.S[10];
                    GraphicsPanel.P[17]= GraphicsPanel.S[10];
                    GraphicsPanel.B[10] = false;
                }

                if((mousePT.getX()>115+50) &&  (mousePT.getX()<145+50) && (mousePT.getY()>155) && (mousePT.getY()<185)){
                    GraphicsPanel.S[12] = !GraphicsPanel.S[12];
                    GraphicsPanel.N[12] = GraphicsPanel.S[12];
                    GraphicsPanel.P[20] = GraphicsPanel.S[12];
                    GraphicsPanel.B[12] = false;
                }

                if((mousePT.getX()>115+50) &&  (mousePT.getX()<145+50) && (mousePT.getY()>125) && (mousePT.getY()<155)){
                    GraphicsPanel.S[14] = !GraphicsPanel.S[14];
                    GraphicsPanel.N[14] = GraphicsPanel.S[14];
                    GraphicsPanel.P[23] =GraphicsPanel.S[14];
                    GraphicsPanel.B[14] = false;
                }

                if((mousePT.getX()>115+50) &&  (mousePT.getX()<145+50) && (mousePT.getY()>95) && (mousePT.getY()<125)){
                    GraphicsPanel.S[16] = !GraphicsPanel.S[16];
                    GraphicsPanel.N[16] = GraphicsPanel.S[16];
                    GraphicsPanel.P[27] = GraphicsPanel.S[16];
                    GraphicsPanel.B[16] = false;
                }

                if((mousePT.getX()>10+50) &&  (mousePT.getX()<30+50) && (mousePT.getY()>300) && (mousePT.getY()<330)){
                    GraphicsPanel.B[3] = !GraphicsPanel.B[3];
                    GraphicsPanel.N[3] = GraphicsPanel.B[3];
                    GraphicsPanel.P[3] = GraphicsPanel.B[3];
                    GraphicsPanel.S[3] = false;
                }

                if((mousePT.getX()>10+50) &&  (mousePT.getX()<30+50) && (mousePT.getY()>270) && (mousePT.getY()<300)){
                    GraphicsPanel.B[5] = !GraphicsPanel.B[5];
                    GraphicsPanel.N[5] = GraphicsPanel.B[5];
                    GraphicsPanel.P[6] = GraphicsPanel.B[5];
                    GraphicsPanel.S[5] = false;
                }

                if((mousePT.getX()>10+50) &&  (mousePT.getX()<30+50) && (mousePT.getY()>240) && (mousePT.getY()<270)){
                    GraphicsPanel.B[7] = !GraphicsPanel.B[7];
                    GraphicsPanel.N[7] = GraphicsPanel.B[7];
                    GraphicsPanel.P[9] = GraphicsPanel.B[7];
                    GraphicsPanel.S[7] = false;
                }

                if((mousePT.getX()>10+50) &&  (mousePT.getX()<30+50) && (mousePT.getY()>210) && (mousePT.getY()<240)){
                    GraphicsPanel.B[9] = !GraphicsPanel.B[9];
                    GraphicsPanel.N[9] = GraphicsPanel.B[9];
                    GraphicsPanel.P[13] = GraphicsPanel.B[9];
                    GraphicsPanel.S[9] = false;
                }

                if((mousePT.getX()>10+50) &&  (mousePT.getX()<30+50) && (mousePT.getY()>180) && (mousePT.getY()<210)){
                    GraphicsPanel.B[11] = !GraphicsPanel.B[11];
                    GraphicsPanel.N[11] = GraphicsPanel.B[11];
                    GraphicsPanel.P[16] = GraphicsPanel.B[11];
                    GraphicsPanel.S[11] = false;
                }

                if((mousePT.getX()>10+50) &&  (mousePT.getX()<30+50) && (mousePT.getY()>150) && (mousePT.getY()<180)){
                    GraphicsPanel.B[13] = !GraphicsPanel.B[13];
                    GraphicsPanel.N[13] = GraphicsPanel.B[13];
                    GraphicsPanel.P[20] = GraphicsPanel.B[13];
                    GraphicsPanel.S[13] = false;
                }

                if((mousePT.getX()>10+50) &&  (mousePT.getX()<30+50) && (mousePT.getY()>120) && (mousePT.getY()<150)){
                    GraphicsPanel.B[15] = !GraphicsPanel.B[15];
                    GraphicsPanel.N[15] = GraphicsPanel.B[15];
                    GraphicsPanel.P[23] = GraphicsPanel.B[15];
                    GraphicsPanel.S[15] = false;
                }

                if((mousePT.getX()>10+50) &&  (mousePT.getX()<30+50) && (mousePT.getY()>90) && (mousePT.getY()<120)){
                    GraphicsPanel.B[17] = !GraphicsPanel.B[17];
                    GraphicsPanel.N[17] = GraphicsPanel.B[17];
                    GraphicsPanel.P[27] = GraphicsPanel.B[17];
                    GraphicsPanel.S[17] = false;
                }

                if((mousePT.getX()>145+50) &&  (mousePT.getX()<175+50) && (mousePT.getY()>315) && (mousePT.getY()<345)){
                    GraphicsPanel.B[2] = !GraphicsPanel.B[2];
                    GraphicsPanel.N[2] = GraphicsPanel.B[2];
                    GraphicsPanel.P[1] = GraphicsPanel.B[2];
                    GraphicsPanel.S[2] = false;
                }

                if((mousePT.getX()>145+50) &&  (mousePT.getX()<175+50) && (mousePT.getY()>285) && (mousePT.getY()<315)){
                    GraphicsPanel.B[4] = !GraphicsPanel.B[4];
                    GraphicsPanel.N[4] = GraphicsPanel.B[4];
                    GraphicsPanel.P[4] = GraphicsPanel.B[4];
                    GraphicsPanel.S[4] = false;
                }

                if((mousePT.getX()>145+50) &&  (mousePT.getX()<175+50) && (mousePT.getY()>255) && (mousePT.getY()<285)){
                    GraphicsPanel.B[6] = !GraphicsPanel.B[6];
                    GraphicsPanel.N[6] = GraphicsPanel.B[6];
                    GraphicsPanel.P[8] = GraphicsPanel.B[6];
                    GraphicsPanel.S[6] = false;
                }

                if((mousePT.getX()>145+50) &&  (mousePT.getX()<175+50) && (mousePT.getY()>225) && (mousePT.getY()<255)){
                    GraphicsPanel.B[8] = !GraphicsPanel.B[8];
                    GraphicsPanel.N[8] = GraphicsPanel.B[8];
                    GraphicsPanel.P[15] = GraphicsPanel.B[8];
                    GraphicsPanel.S[8] = false;
                }

                if((mousePT.getX()>145+50) &&  (mousePT.getX()<175+50) && (mousePT.getY()>185) && (mousePT.getY()<225))
                {
                    GraphicsPanel.B[10] = !GraphicsPanel.B[10];
                    GraphicsPanel.N[10] = GraphicsPanel.B[10];
                    GraphicsPanel.P[15] = GraphicsPanel.B[10];
                    GraphicsPanel.S[10] = false;
                }

                if((mousePT.getX()>145+50) &&  (mousePT.getX()<175+50) && (mousePT.getY()>155) && (mousePT.getY()<185)){
                    GraphicsPanel.B[12] = !GraphicsPanel.B[12];
                    GraphicsPanel.N[12] = GraphicsPanel.B[12];
                    GraphicsPanel.P[18] = GraphicsPanel.B[12];
                    GraphicsPanel.S[12] = false;
                }

                if((mousePT.getX()>145+50) &&  (mousePT.getX()<175+50) && (mousePT.getY()>125) && (mousePT.getY()<155)){
                    GraphicsPanel.B[14] = !GraphicsPanel.B[14];
                    GraphicsPanel.N[14] = GraphicsPanel.B[14];
                    GraphicsPanel.P[21] = GraphicsPanel.B[14];
                    GraphicsPanel.S[14] = false;
                }

                if((mousePT.getX()>145+50) &&  (mousePT.getX()<175+50) && (mousePT.getY()>95) && (mousePT.getY()<125)){
                    GraphicsPanel.B[16] = !GraphicsPanel.B[16];
                    GraphicsPanel.N[16] = GraphicsPanel.B[16];
                    GraphicsPanel.P[25] =GraphicsPanel.B[16];
                    GraphicsPanel.S[16]= false;
                }

                if((mousePT.getX()>145+50) &&  (mousePT.getX()<175+50) && (mousePT.getY()>65) && (mousePT.getY()<95)){
                    GraphicsPanel.B[18] = !GraphicsPanel.B[18];
                    GraphicsPanel.N[18] = GraphicsPanel.B[18];
                    GraphicsPanel.P[28] = GraphicsPanel.B[18];
                }

                panel.repaint();
            }
        });

        convert= new JButton("best tab");
        clear = new JButton("Clear");
        makeTABfile = new JButton("makeTABfile");
        barline = new JButton("Insert bar '|'");
        convert_all = new JButton("all tabs");
        convert_to_piano = new JButton("piano note");


        convert_to_piano.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent arg0)
            {
                if(GraphicsPanel.lowG) {
                    if (tf1.getText().matches(".*\\d.*")){
                        int tab1 = Integer.parseInt(tf1.getText().trim());
                        switch (tab1) {
                            case 0: GraphicsPanel.N[9] = true; GraphicsPanel.B[9] = false; GraphicsPanel.S[9]= false; GraphicsPanel.P[14] = true; break;
                            case 1: GraphicsPanel.N[9] = true; GraphicsPanel.B[9] = false; GraphicsPanel.S[9]= true; GraphicsPanel.P[15] = true; break;
                            case 2: GraphicsPanel.N[10] = true; GraphicsPanel.B[10] = false; GraphicsPanel.S[10]= false; GraphicsPanel.P[16] = true; break;
                            case 3: GraphicsPanel.N[11] = true; GraphicsPanel.B[11] = false; GraphicsPanel.S[11]= false; GraphicsPanel.P[17] = true; break;
                            case 4: GraphicsPanel.N[11] = true; GraphicsPanel.B[11] = false; GraphicsPanel.S[11]= true; GraphicsPanel.P[18] = true; break;
                            case 5: GraphicsPanel.N[12] = true; GraphicsPanel.B[12] = false; GraphicsPanel.S[12]= false; GraphicsPanel.P[19] = true; break;
                            case 6: GraphicsPanel.N[12] = true; GraphicsPanel.B[12] = false; GraphicsPanel.S[12]= true; GraphicsPanel.P[20] = true; break;
                            case 7: GraphicsPanel.N[13] = true; GraphicsPanel.B[13] = false; GraphicsPanel.S[13]= false; GraphicsPanel.P[21] = true; break;
                            case 8: GraphicsPanel.N[14] = true; GraphicsPanel.B[14] = false; GraphicsPanel.S[14]= false; GraphicsPanel.P[22] = true; break;
                            case 9: GraphicsPanel.N[14] = true; GraphicsPanel.B[14] = false; GraphicsPanel.S[14]= true; GraphicsPanel.P[23] = true; break;
                            case 10: GraphicsPanel.N[15] = true; GraphicsPanel.B[15] = false; GraphicsPanel.S[15]= false; GraphicsPanel.P[24] = true; break;
                            case 11: GraphicsPanel.N[15] = true; GraphicsPanel.B[15] = false; GraphicsPanel.S[15]= true; GraphicsPanel.P[25] = true; break;
                            case 12: GraphicsPanel.N[16] = true; GraphicsPanel.B[16] = false; GraphicsPanel.S[16]= false; GraphicsPanel.P[26] = true; break;
                            case 13: GraphicsPanel.N[16] = true; GraphicsPanel.B[16] = false; GraphicsPanel.S[16]= true; GraphicsPanel.P[27] = true; break;
                            case 14: GraphicsPanel.N[17] = true; GraphicsPanel.B[17] = false; GraphicsPanel.S[17] = false; GraphicsPanel.P[28] = true; break;
                            case 15: GraphicsPanel.N[18] = true; GraphicsPanel.B[18] = false; break;
                        }
                    }

                    if(tf2.getText().matches(".*\\d.*")) {
                        int tab2 = Integer.parseInt(tf2.getText().trim());
                        switch (tab2) {
                            case 0: GraphicsPanel.N[6] = true; GraphicsPanel.B[6] = false; GraphicsPanel.S[6]= false; GraphicsPanel.P[9] = true; break;
                            case 1: GraphicsPanel.N[7] = true; GraphicsPanel.B[7] = false; GraphicsPanel.S[7]= false; GraphicsPanel.P[10] = true; break;
                            case 2: GraphicsPanel.N[7] = true; GraphicsPanel.B[7] = false; GraphicsPanel.S[7]= true; GraphicsPanel.P[11] = true; break;
                            case 3: GraphicsPanel.N[8] = true; GraphicsPanel.B[8] = false; GraphicsPanel.S[8]= false; GraphicsPanel.P[12] = true; break;
                            case 4: GraphicsPanel.N[8] = true; GraphicsPanel.B[8] = false; GraphicsPanel.S[8]= true; GraphicsPanel.P[13] = true; break;
                            case 5: GraphicsPanel.N[9] = true; GraphicsPanel.B[9] = false; GraphicsPanel.S[9]= false; GraphicsPanel.P[14] = true; break;
                            case 6: GraphicsPanel.N[9] = true; GraphicsPanel.B[9] = false; GraphicsPanel.S[9]= true; GraphicsPanel.P[15] = true; break;
                            case 7: GraphicsPanel.N[10] = true; GraphicsPanel.B[10] = false; GraphicsPanel.S[10]= false; GraphicsPanel.P[16] = true; break;
                            case 8: GraphicsPanel.N[11] = true; GraphicsPanel.B[11] = false; GraphicsPanel.S[11]= false; GraphicsPanel.P[17] = true; break;
                            case 9: GraphicsPanel.N[11] = true; GraphicsPanel.B[11] = false; GraphicsPanel.S[11]= true; GraphicsPanel.P[18] = true; break;
                            case 10: GraphicsPanel.N[12] = true; GraphicsPanel.B[12] = false; GraphicsPanel.S[12]= false; GraphicsPanel.P[19] = true; break;
                            case 11: GraphicsPanel.N[12] = true; GraphicsPanel.B[12] = false; GraphicsPanel.S[12]= true; GraphicsPanel.P[20] = true; break;
                            case 12: GraphicsPanel.N[13] = true; GraphicsPanel.B[13] = false; GraphicsPanel.S[13]= false; GraphicsPanel.P[21] = true; break;
                            case 13: GraphicsPanel.N[14] = true; GraphicsPanel.B[14] = false; GraphicsPanel.S[14]= false; GraphicsPanel.P[22] = true; break;
                            case 14: GraphicsPanel.N[14] = true; GraphicsPanel.B[14] = false; GraphicsPanel.S[14] = true; GraphicsPanel.P[23] = true; break;
                            case 15: GraphicsPanel.N[15] = true; GraphicsPanel.B[15] = false; GraphicsPanel.S[15] = false; GraphicsPanel.P[24] = true; break;
                        }
                    }

                    if(tf3.getText().matches(".*\\d.*")){
                        int tab3 = Integer.parseInt(tf3.getText().trim());
                        switch (tab3) {
                            case 0: GraphicsPanel.N[4] = true; GraphicsPanel.B[4] = false; GraphicsPanel.S[4]= false; GraphicsPanel.P[5] = true; break;
                            case 1: GraphicsPanel.N[4] = true; GraphicsPanel.B[4] = false; GraphicsPanel.S[4]= true; GraphicsPanel.P[6] = true; break;
                            case 2: GraphicsPanel.N[5] = true; GraphicsPanel.B[5] = false; GraphicsPanel.S[5]= false; GraphicsPanel.P[7] = true; break;
                            case 3: GraphicsPanel.N[5] = true; GraphicsPanel.B[5] = false; GraphicsPanel.S[5]= true; GraphicsPanel.P[8] = true; break;
                            case 4: GraphicsPanel.N[6] = true; GraphicsPanel.B[6] = false; GraphicsPanel.S[6]= false; GraphicsPanel.P[9] = true; break;
                            case 5: GraphicsPanel.N[7] = true; GraphicsPanel.B[7] = false; GraphicsPanel.S[7]= false; GraphicsPanel.P[10] = true; break;
                            case 6: GraphicsPanel.N[7] = true; GraphicsPanel.B[7] = false; GraphicsPanel.S[7]= true; GraphicsPanel.P[11] = true; break;
                            case 7: GraphicsPanel.N[8] = true; GraphicsPanel.B[8] = false; GraphicsPanel.S[8]= false; GraphicsPanel.P[12] = true; break;
                            case 8: GraphicsPanel.N[8] = true; GraphicsPanel.B[8] = false; GraphicsPanel.S[8]= true; GraphicsPanel.P[13] = true; break;
                            case 9: GraphicsPanel.N[9] = true; GraphicsPanel.B[9] = false; GraphicsPanel.S[9]= false; GraphicsPanel.P[14] = true; break;
                            case 10: GraphicsPanel.N[9] = true; GraphicsPanel.B[9] = false; GraphicsPanel.S[9]= true; GraphicsPanel.P[15] = true; break;
                            case 11: GraphicsPanel.N[10] = true; GraphicsPanel.B[10] = false; GraphicsPanel.S[10]= false; GraphicsPanel.P[16] = true; break;
                            case 12: GraphicsPanel.N[11] = true; GraphicsPanel.B[11] = false; GraphicsPanel.S[11]= false; GraphicsPanel.P[17] = true; break;
                            case 13: GraphicsPanel.N[11] = true; GraphicsPanel.B[11] = false; GraphicsPanel.S[11]= true; GraphicsPanel.P[18] = true; break;
                            case 14: GraphicsPanel.N[12] = true; GraphicsPanel.B[12] = false; GraphicsPanel.S[12] = false; GraphicsPanel.P[19] = true; break;
                            case 15: GraphicsPanel.N[12] = true; GraphicsPanel.B[12] = false; GraphicsPanel.S[12] = true; GraphicsPanel.P[20] = true; break;
                            case 16: GraphicsPanel.N[13] = true; GraphicsPanel.B[13] = false; GraphicsPanel.S[12] = false; GraphicsPanel.P[21] = true; break;
                            case 17: GraphicsPanel.N[13] = true; GraphicsPanel.B[13] = false; GraphicsPanel.S[13] = true; GraphicsPanel.P[22] = true; break;
                        }
                    }

                    if(tf4.getText().matches(".*\\d.*")){
                        int tab4 = Integer.parseInt(tf4.getText().trim());
                        switch (tab4) {
                            case 0: GraphicsPanel.N[1] = true;  GraphicsPanel.S[1]= false; GraphicsPanel.P[0] = true; break;
                            case 1: GraphicsPanel.N[1] = true;  GraphicsPanel.S[1]= true; GraphicsPanel.P[1] = true; break;
                            case 2: GraphicsPanel.N[2] = true; GraphicsPanel.B[2] = false; GraphicsPanel.S[2]= false; GraphicsPanel.P[2] = true; break;
                            case 3: GraphicsPanel.N[2] = true; GraphicsPanel.B[2] = false; GraphicsPanel.S[2]= true; GraphicsPanel.P[3] = true; break;
                            case 4: GraphicsPanel.N[3] = true; GraphicsPanel.B[3] = false; GraphicsPanel.S[3]= false; GraphicsPanel.P[4] = true; break;
                            case 5: GraphicsPanel.N[4] = true; GraphicsPanel.B[4] = false; GraphicsPanel.S[4]= false; GraphicsPanel.P[5] = true; break;
                            case 6: GraphicsPanel.N[4] = true; GraphicsPanel.B[4] = false; GraphicsPanel.S[4]= true; GraphicsPanel.P[6] = true; break;
                            case 7: GraphicsPanel.N[5] = true; GraphicsPanel.B[5] = false; GraphicsPanel.S[5]= false; GraphicsPanel.P[7] = true; break;
                            case 8: GraphicsPanel.N[5] = true; GraphicsPanel.B[5] = false; GraphicsPanel.S[5]= true; GraphicsPanel.P[8] = true; break;
                            case 9: GraphicsPanel.N[6] = true; GraphicsPanel.B[6] = false; GraphicsPanel.S[6]= false; GraphicsPanel.P[9] = true; break;
                            case 10: GraphicsPanel.N[7] = true; GraphicsPanel.B[7] = false; GraphicsPanel.S[7]= false; GraphicsPanel.P[10] = true; break;
                            case 11: GraphicsPanel.N[7] = true; GraphicsPanel.B[7] = false; GraphicsPanel.S[7]= true; GraphicsPanel.P[11] = true; break;
                            case 12: GraphicsPanel.N[8] = true; GraphicsPanel.B[8] = false; GraphicsPanel.S[8]= false; GraphicsPanel.P[12] = true; break;
                        }
                    }
                }

                if(GraphicsPanel.highG) {
                    if (tf1.getText().matches(".*\\d.*")){
                        int tab1 = Integer.parseInt(tf1.getText().trim());
                        switch (tab1) {
                            case 0: GraphicsPanel.N[9] = true; GraphicsPanel.B[9] = false; GraphicsPanel.S[9]= false; GraphicsPanel.P[14] = true; break;
                            case 1: GraphicsPanel.N[9] = true; GraphicsPanel.B[9] = false; GraphicsPanel.S[9]= true; GraphicsPanel.P[15] = true; break;
                            case 2: GraphicsPanel.N[10] = true; GraphicsPanel.B[10] = false; GraphicsPanel.S[10]= false; GraphicsPanel.P[16] = true; break;
                            case 3: GraphicsPanel.N[11] = true; GraphicsPanel.B[11] = false; GraphicsPanel.S[11]= false; GraphicsPanel.P[17] = true; break;
                            case 4: GraphicsPanel.N[11] = true; GraphicsPanel.B[11] = false; GraphicsPanel.S[11]= true; GraphicsPanel.P[18] = true; break;
                            case 5: GraphicsPanel.N[12] = true; GraphicsPanel.B[12] = false; GraphicsPanel.S[12]= false; GraphicsPanel.P[19] = true; break;
                            case 6: GraphicsPanel.N[12] = true; GraphicsPanel.B[12] = false; GraphicsPanel.S[12]= true; GraphicsPanel.P[20] = true; break;
                            case 7: GraphicsPanel.N[13] = true; GraphicsPanel.B[13] = false; GraphicsPanel.S[13]= false; GraphicsPanel.P[21] = true; break;
                            case 8: GraphicsPanel.N[14] = true; GraphicsPanel.B[14] = false; GraphicsPanel.S[14]= false; GraphicsPanel.P[22] = true; break;
                            case 9: GraphicsPanel.N[14] = true; GraphicsPanel.B[14] = false; GraphicsPanel.S[14]= true; GraphicsPanel.P[23] = true; break;
                            case 10: GraphicsPanel.N[15] = true; GraphicsPanel.B[15] = false; GraphicsPanel.S[15]= false; GraphicsPanel.P[24] = true; break;
                            case 11: GraphicsPanel.N[15] = true; GraphicsPanel.B[15] = false; GraphicsPanel.S[15]= true; GraphicsPanel.P[25] = true; break;
                            case 12: GraphicsPanel.N[16] = true; GraphicsPanel.B[16] = false; GraphicsPanel.S[16]= false; GraphicsPanel.P[26] = true; break;
                            case 13: GraphicsPanel.N[16] = true; GraphicsPanel.B[16] = false; GraphicsPanel.S[16]= true; GraphicsPanel.P[27] = true; break;
                            case 14: GraphicsPanel.N[17] = true; GraphicsPanel.B[17] = false; GraphicsPanel.S[17] = false; GraphicsPanel.P[28] = true; break;
                            case 15: GraphicsPanel.N[18] = true; GraphicsPanel.B[18] = false; break;
                        }
                    }

                    if(tf2.getText().matches(".*\\d.*")) {
                        int tab2 = Integer.parseInt(tf2.getText().trim());
                        switch (tab2) {
                            case 0: GraphicsPanel.N[6] = true; GraphicsPanel.B[6] = false; GraphicsPanel.S[6]= false; GraphicsPanel.P[9] = true; break;
                            case 1: GraphicsPanel.N[7] = true; GraphicsPanel.B[7] = false; GraphicsPanel.S[7]= false; GraphicsPanel.P[10] = true; break;
                            case 2: GraphicsPanel.N[7] = true; GraphicsPanel.B[7] = false; GraphicsPanel.S[7]= true; GraphicsPanel.P[11] = true; break;
                            case 3: GraphicsPanel.N[8] = true; GraphicsPanel.B[8] = false; GraphicsPanel.S[8]= false; GraphicsPanel.P[12] = true; break;
                            case 4: GraphicsPanel.N[8] = true; GraphicsPanel.B[8] = false; GraphicsPanel.S[8]= true; GraphicsPanel.P[13] = true; break;
                            case 5: GraphicsPanel.N[9] = true; GraphicsPanel.B[9] = false; GraphicsPanel.S[9]= false; GraphicsPanel.P[14] = true; break;
                            case 6: GraphicsPanel.N[9] = true; GraphicsPanel.B[9] = false; GraphicsPanel.S[9]= true; GraphicsPanel.P[15] = true; break;
                            case 7: GraphicsPanel.N[10] = true; GraphicsPanel.B[10] = false; GraphicsPanel.S[10]= false; GraphicsPanel.P[16] = true; break;
                            case 8: GraphicsPanel.N[11] = true; GraphicsPanel.B[11] = false; GraphicsPanel.S[11]= false; GraphicsPanel.P[17] = true; break;
                            case 9: GraphicsPanel.N[11] = true; GraphicsPanel.B[11] = false; GraphicsPanel.S[11]= true; GraphicsPanel.P[18] = true; break;
                            case 10: GraphicsPanel.N[12] = true; GraphicsPanel.B[12] = false; GraphicsPanel.S[12]= false; GraphicsPanel.P[19] = true; break;
                            case 11: GraphicsPanel.N[12] = true; GraphicsPanel.B[12] = false; GraphicsPanel.S[12]= true; GraphicsPanel.P[20] = true; break;
                            case 12: GraphicsPanel.N[13] = true; GraphicsPanel.B[13] = false; GraphicsPanel.S[13]= false; GraphicsPanel.P[21] = true; break;
                            case 13: GraphicsPanel.N[14] = true; GraphicsPanel.B[14] = false; GraphicsPanel.S[14]= false; GraphicsPanel.P[22] = true; break;
                            case 14: GraphicsPanel.N[14] = true; GraphicsPanel.B[14] = false; GraphicsPanel.S[14] = true; GraphicsPanel.P[23] = true; break;
                            case 15: GraphicsPanel.N[15] = true; GraphicsPanel.B[15] = false; GraphicsPanel.S[15] = false; GraphicsPanel.P[24] = true; break;
                        }
                    }

                    if(tf3.getText().matches(".*\\d.*")){
                        int tab3 = Integer.parseInt(tf3.getText().trim());
                        switch (tab3) {
                            case 0: GraphicsPanel.N[4] = true; GraphicsPanel.B[4] = false; GraphicsPanel.S[4]= false; GraphicsPanel.P[5] = true; break;
                            case 1: GraphicsPanel.N[4] = true; GraphicsPanel.B[4] = false; GraphicsPanel.S[4]= true; GraphicsPanel.P[6] = true; break;
                            case 2: GraphicsPanel.N[5] = true; GraphicsPanel.B[5] = false; GraphicsPanel.S[5]= false; GraphicsPanel.P[7] = true; break;
                            case 3: GraphicsPanel.N[5] = true; GraphicsPanel.B[5] = false; GraphicsPanel.S[5]= true; GraphicsPanel.P[8] = true; break;
                            case 4: GraphicsPanel.N[6] = true; GraphicsPanel.B[6] = false; GraphicsPanel.S[6]= false; GraphicsPanel.P[9] = true; break;
                            case 5: GraphicsPanel.N[7] = true; GraphicsPanel.B[7] = false; GraphicsPanel.S[7]= false; GraphicsPanel.P[10] = true; break;
                            case 6: GraphicsPanel.N[7] = true; GraphicsPanel.B[7] = false; GraphicsPanel.S[7]= true; GraphicsPanel.P[11] = true; break;
                            case 7: GraphicsPanel.N[8] = true; GraphicsPanel.B[8] = false; GraphicsPanel.S[8]= false; GraphicsPanel.P[12] = true; break;
                            case 8: GraphicsPanel.N[8] = true; GraphicsPanel.B[8] = false; GraphicsPanel.S[8]= true; GraphicsPanel.P[13] = true; break;
                            case 9: GraphicsPanel.N[9] = true; GraphicsPanel.B[9] = false; GraphicsPanel.S[9]= false; GraphicsPanel.P[14] = true; break;
                            case 10: GraphicsPanel.N[9] = true; GraphicsPanel.B[9] = false; GraphicsPanel.S[9]= true; GraphicsPanel.P[15] = true; break;
                            case 11: GraphicsPanel.N[10] = true; GraphicsPanel.B[10] = false; GraphicsPanel.S[10]= false; GraphicsPanel.P[16] = true; break;
                            case 12: GraphicsPanel.N[11] = true; GraphicsPanel.B[11] = false; GraphicsPanel.S[11]= false; GraphicsPanel.P[17] = true; break;
                            case 13: GraphicsPanel.N[11] = true; GraphicsPanel.B[11] = false; GraphicsPanel.S[11]= true; GraphicsPanel.P[18] = true; break;
                            case 14: GraphicsPanel.N[12] = true; GraphicsPanel.B[12] = false; GraphicsPanel.S[12] = false; GraphicsPanel.P[19] = true; break;
                            case 15: GraphicsPanel.N[12] = true; GraphicsPanel.B[12] = false; GraphicsPanel.S[12] = true; GraphicsPanel.P[20] = true; break;
                            case 16: GraphicsPanel.N[13] = true; GraphicsPanel.B[13] = false; GraphicsPanel.S[12] = false; GraphicsPanel.P[21] = true; break;
                            case 17: GraphicsPanel.N[13] = true; GraphicsPanel.B[13] = false; GraphicsPanel.S[13] = true; GraphicsPanel.P[22] = true; break;
                        }
                    }

                    if(tf4.getText().matches(".*\\d.*")){
                        int tab4 = Integer.parseInt(tf4.getText().trim());
                        switch (tab4) {
                            case 0: GraphicsPanel.N[8] = true;  GraphicsPanel.B[8] = false; GraphicsPanel.S[8]= false; GraphicsPanel.P[12] = true; break;
                            case 1: GraphicsPanel.N[8] = true;  GraphicsPanel.B[8]= false; GraphicsPanel.S[8] = true; GraphicsPanel.P[13] = true; break;
                            case 2: GraphicsPanel.N[9] = true; GraphicsPanel.B[9] = false; GraphicsPanel.S[9]= false; GraphicsPanel.P[14] = true; break;
                            case 3: GraphicsPanel.N[9] = true; GraphicsPanel.B[9] = false; GraphicsPanel.S[9]= true; GraphicsPanel.P[15] = true; break;
                            case 4: GraphicsPanel.N[10] = true; GraphicsPanel.B[10] = false; GraphicsPanel.S[10]= false; GraphicsPanel.P[16] = true; break;
                            case 5: GraphicsPanel.N[4] = true; GraphicsPanel.B[4] = false; GraphicsPanel.S[4]= false; GraphicsPanel.P[5] = true; break;
                            case 6: GraphicsPanel.N[4] = true; GraphicsPanel.B[4] = false; GraphicsPanel.S[4]= true; GraphicsPanel.P[6] = true; break;
                            case 7: GraphicsPanel.N[5] = true; GraphicsPanel.B[5] = false; GraphicsPanel.S[5]= false; GraphicsPanel.P[7] = true; break;
                            case 8: GraphicsPanel.N[5] = true; GraphicsPanel.B[5] = false; GraphicsPanel.S[5]= true; GraphicsPanel.P[8] = true; break;
                            case 9: GraphicsPanel.N[6] = true; GraphicsPanel.B[6] = false; GraphicsPanel.S[6]= false; GraphicsPanel.P[9] = true; break;
                            case 10: GraphicsPanel.N[7] = true; GraphicsPanel.B[7] = false; GraphicsPanel.S[7]= false; GraphicsPanel.P[10] = true; break;
                            case 11: GraphicsPanel.N[7] = true; GraphicsPanel.B[7] = false; GraphicsPanel.S[7]= true; GraphicsPanel.P[11] = true; break;
                        }
                    }
                }
                
                panel.repaint();
            }
        });

        clear.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent arg0)
            {
                save_tab();
                tf1.setText("    ");
                tf2.setText("    ");
                tf3.setText("    ");
                tf4.setText("    ");
                show_popup_menu = false;
                pm.setVisible(false);
                panel.repaint();
            }
        });

        convert.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if((GraphicsPanel.N[1])&&(GraphicsPanel.S[1])) GraphicsPanel.P[0]=false;
                if((GraphicsPanel.N[2])&&((GraphicsPanel.S[2]) || (GraphicsPanel.B[2])))  GraphicsPanel.P[2]=false;
                if((GraphicsPanel.N[3])&&((GraphicsPanel.S[3]) || (GraphicsPanel.B[3]))) GraphicsPanel.P[4]=false;
                if((GraphicsPanel.N[4])&&((GraphicsPanel.S[4]) || (GraphicsPanel.B[4]))) GraphicsPanel.P[5]=false;
                if((GraphicsPanel.N[5])&&((GraphicsPanel.S[5]) || (GraphicsPanel.B[5]))) GraphicsPanel.P[7]=false;
                if((GraphicsPanel.N[6])&&((GraphicsPanel.S[6]) || (GraphicsPanel.B[6]))) GraphicsPanel.P[9]=false;
                if((GraphicsPanel.N[7])&&((GraphicsPanel.S[7]) || (GraphicsPanel.B[7]))) GraphicsPanel.P[10]=false;
                if((GraphicsPanel.N[8])&&((GraphicsPanel.S[8]) || (GraphicsPanel.B[8]))) GraphicsPanel.P[12]=false;
                if((GraphicsPanel.N[9])&&((GraphicsPanel.S[9]) || (GraphicsPanel.B[9]))) GraphicsPanel.P[14]=false;
                if((GraphicsPanel.N[10])&&((GraphicsPanel.S[10]) || (GraphicsPanel.B[10]))) GraphicsPanel.P[16]=false;
                if((GraphicsPanel.N[11])&&((GraphicsPanel.S[11]) || (GraphicsPanel.B[11]))) GraphicsPanel.P[17]=false;
                if((GraphicsPanel.N[12])&&((GraphicsPanel.S[12]) || (GraphicsPanel.B[12]))) GraphicsPanel.P[19]=false;
                if((GraphicsPanel.N[13])&&((GraphicsPanel.S[13]) || (GraphicsPanel.B[13]))) GraphicsPanel.P[21]=false;
                if((GraphicsPanel.N[14])&&((GraphicsPanel.S[14]) || (GraphicsPanel.B[14]))) GraphicsPanel.P[22]=false;
                if((GraphicsPanel.N[15])&&((GraphicsPanel.S[15]) || (GraphicsPanel.B[15])))  GraphicsPanel.P[24]=false;
                if((GraphicsPanel.N[16])&&((GraphicsPanel.S[16]) || (GraphicsPanel.B[16]))) GraphicsPanel.P[26]=false;
                if((GraphicsPanel.N[17])&&((GraphicsPanel.S[17]) || (GraphicsPanel.B[17]))) GraphicsPanel.P[28]=false;
                if((GraphicsPanel.N[18])&&(GraphicsPanel.B[18])) GraphicsPanel.P[29]=false;

                find_best_tab();
                find_all_tabs();
                GraphicsPanel.showBestTab = true;
                panel.repaint();
            }
        });

        convert_all.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if((GraphicsPanel.N[1])&&(GraphicsPanel.S[1])) GraphicsPanel.P[0]=false;
                if((GraphicsPanel.N[2])&&((GraphicsPanel.S[2]) || (GraphicsPanel.B[2])))  GraphicsPanel.P[2]=false;
                if((GraphicsPanel.N[3])&&((GraphicsPanel.S[3]) || (GraphicsPanel.B[3]))) GraphicsPanel.P[4]=false;
                if((GraphicsPanel.N[4])&&((GraphicsPanel.S[4]) || (GraphicsPanel.B[4]))) GraphicsPanel.P[5]=false;
                if((GraphicsPanel.N[5])&&((GraphicsPanel.S[5]) || (GraphicsPanel.B[5]))) GraphicsPanel.P[7]=false;
                if((GraphicsPanel.N[6])&&((GraphicsPanel.S[6]) || (GraphicsPanel.B[6]))) GraphicsPanel.P[9]=false;
                if((GraphicsPanel.N[7])&&((GraphicsPanel.S[7]) || (GraphicsPanel.B[7]))) GraphicsPanel.P[10]=false;
                if((GraphicsPanel.N[8])&&((GraphicsPanel.S[8]) || (GraphicsPanel.B[8]))) GraphicsPanel.P[12]=false;
                if((GraphicsPanel.N[9])&&((GraphicsPanel.S[9]) || (GraphicsPanel.B[9]))) GraphicsPanel.P[14]=false;
                if((GraphicsPanel.N[10])&&((GraphicsPanel.S[10]) || (GraphicsPanel.B[10]))) GraphicsPanel.P[16]=false;
                if((GraphicsPanel.N[11])&&((GraphicsPanel.S[11]) || (GraphicsPanel.B[11]))) GraphicsPanel.P[17]=false;
                if((GraphicsPanel.N[12])&&((GraphicsPanel.S[12]) || (GraphicsPanel.B[12]))) GraphicsPanel.P[19]=false;
                if((GraphicsPanel.N[13])&&((GraphicsPanel.S[13]) || (GraphicsPanel.B[13]))) GraphicsPanel.P[21]=false;
                if((GraphicsPanel.N[14])&&((GraphicsPanel.S[14]) || (GraphicsPanel.B[14]))) GraphicsPanel.P[22]=false;
                if((GraphicsPanel.N[15])&&((GraphicsPanel.S[15]) || (GraphicsPanel.B[15])))  GraphicsPanel.P[24]=false;
                if((GraphicsPanel.N[16])&&((GraphicsPanel.S[16]) || (GraphicsPanel.B[16]))) GraphicsPanel.P[26]=false;
                if((GraphicsPanel.N[17])&&((GraphicsPanel.S[17]) || (GraphicsPanel.B[17]))) GraphicsPanel.P[28]=false;
                if((GraphicsPanel.N[18])&&(GraphicsPanel.B[18])) GraphicsPanel.P[29]=false;

                find_all_tabs();
                GraphicsPanel.showAllTabs = true;
                panel.repaint();
            }
        });

        barline.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent arg0)
            {
                insert_bar = true;
                clear.doClick();
            }
        });

        makeTABfile.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent arg0)
            {
                File file = new File("temp.txt"); 
                File f = new File("TABS.txt");

                if(f.exists() && !f.isDirectory())
                {
                    f.delete();
                }

                File fileout = new File("TABS.txt");
                int lines = 0;
                BufferedReader countlines;
                
                try
                {
                    countlines = new BufferedReader(new FileReader(file));
                    while ((countlines.readLine()) != null)
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

        button_panel = new JPanel();
        button_panel.add(highG);
        button_panel.add(lowG);
        button_panel.add(selectmusicXML);

        button_panel.add(convert);
        button_panel.add(convert_all);
        button_panel.add(convert_to_piano);
        button_panel.add(clear);
        button_panel.add(makeTABfile);
        button_panel.add(barline);
        panel.add(button_panel);

        pm = new JPopupMenu();

        l1 = new JLabel("String 1:");
        p1 = new JPanel();
        p1.add(l1);
        tf1 = new JTextField();
        tf1.setText("     ");
        p1.add(tf1);
        pm.add(p1);

        l2 = new JLabel("String 2:");
        p2 = new JPanel();
        p2.add(l2);
        tf2 = new JTextField();
        tf2.setText("     ");
        p2.add(tf2);
        pm.add(p2);

        l3 = new JLabel("String 3:");
        p3 = new JPanel();
        p3.add(l3);
        tf3 = new JTextField();
        tf3.setText("     ");
        p3.add(tf3);
        pm.add(p3);

        l4 = new JLabel("String 4:");
        p4 = new JPanel();
        p4.add(l4);
        tf4 = new JTextField();
        tf4.setText("     ");
        p4.add(tf4);
      
        pm.add(p4);
        panel.add(pm);
        pm.setVisible(false);
        panel.repaint();

        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
    }
    
    /**
     * Class <code>tabs_Array</code> creates arrays for tabs. 
     * @author Christine Merkel
     *
     */
    public static class tabs_Array {
    	public tabs_Array(int n1, int n2, int n3, int n4)
        {
            items = new int[]{n1,n2,n3,n4};
        }

        private int items[];
        public  int[] getItems() { return items; }
    }
}

