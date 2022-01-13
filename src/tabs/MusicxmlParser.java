package tabs;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Class <code>MusicXML_parser</code> parses musicxml files. 
 * @author Christine Merkel
 *
 */
public class MusicxmlParser {
    public static String piano_part_id ="";
    public static String step = "";
    public static String octave= "";
    public static String alter = "";
    public static Node pitch = null;
    public static Node staff = null;
    public static boolean chord = false;
    public static boolean alter_ = false;
    
    public void parse(File xmlfile){
		boolean line_end = false;
        int last= -1; //0 if 'no', 1 if 'chord'
        int now = -1; //0 if 'no', 1 if 'chord'

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try
        {
            File filepitch = new File("pitch.txt");
            BufferedWriter out = new BufferedWriter(new FileWriter(filepitch, true));
            dbf.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc= db.parse(xmlfile);
            doc.getDocumentElement().normalize();

            NodeList testlist =  doc.getElementsByTagName("part-name");

            for(int i=0;i<testlist.getLength();i++)
            {
                if((testlist.item(i).getTextContent().contains("Pia"))||(testlist.item(i).getTextContent().compareTo("Klavier")==0))
                {
                    Node scorepart = testlist.item(i).getParentNode();
                    Element scorepart_element = (Element) scorepart;
                    piano_part_id = scorepart_element.getAttribute("id");
                }
            }

            if(piano_part_id.compareTo("")==0) {
                NodeList instrumentlist =  doc.getElementsByTagName("instrument-name");

                for(int j=0;j<instrumentlist.getLength();j++) {
                    if((instrumentlist.item(j).getTextContent().contains("Klavier")) || (instrumentlist.item(j).getTextContent().contains("Piano"))) {
                        Node parent = instrumentlist.item(j).getParentNode().getParentNode();
                        Element parent_element = (Element) parent;
                        piano_part_id = parent_element.getAttribute("id");
                    }
                }
            }

            NodeList partlist = doc.getElementsByTagName("part");

            for(int j=0;j<partlist.getLength();j++)
            {
                Node part = partlist.item(j);
                Element part_element = (Element) part;

                if(part_element.getAttribute("id").compareTo(piano_part_id)==0)
                {
                    for(int t=0;t<part.getChildNodes().getLength();t++)
                    {
                        if(part.getChildNodes().item(t).getNodeName().compareTo("measure") == 0)
                        {
                            Node measure = part.getChildNodes().item(t);
                            for(int s=0;s<measure.getChildNodes().getLength();s++)
                            {
                                if(measure.getChildNodes().item(s).getNodeName().compareTo("note")==0)
                                {
                                    Node note = measure.getChildNodes().item(s);
                                    chord = false; pitch = null; octave = ""; alter = ""; step = ""; alter_=false;

                                    for(int u=0;u<note.getChildNodes().getLength();u++)
                                    {
                                        if(note.getChildNodes().item(u).getNodeName().compareTo("chord")==0)  chord = true;

                                        if(note.getChildNodes().item(u).getNodeName().compareTo("pitch")==0)  pitch = note.getChildNodes().item(u);

                                        if((note.getChildNodes().item(u).getNodeName().compareTo("staff")==0) && (pitch!=null) &&
                                                (Integer.valueOf(note.getChildNodes().item(u).getTextContent())==1))
                                        {
                                            for(int z=0;z<pitch.getChildNodes().getLength();z++)
                                            {
                                                if(pitch.getChildNodes().item(z).getNodeName().compareTo("alter")==0)
                                                {
                                                    alter = pitch.getChildNodes().item(z).getTextContent();
                                                    alter_ = true;
                                                }

                                                if(pitch.getChildNodes().item(z).getNodeName().compareTo("step")==0)
                                                {
                                                    step = pitch.getChildNodes().item(z).getTextContent();
                                                }

                                                if(pitch.getChildNodes().item(z).getNodeName().compareTo("octave")==0)
                                                {
                                                    octave = pitch.getChildNodes().item(z).getTextContent();
                                                }
                                            }

                                            String str = step+octave;
                                            if(alter_) str = str+alter;
                                            if(!alter_) str = str+"no";
                                            if(!chord) now = 0;
                                            if(chord) now = 1;
                                            if(((now==0) && (last==0)) || ((last==1) && (now==0)) )line_end = true;
                                            else line_end = false;
                                            if(line_end)
                                            {
                                                out.newLine();
                                                out.write(str+" ");
                                            }
                                            if(!line_end) out.write(str+" ");
                                            last = now;
                                            alter_ = false;
                                            chord = false;
                                        }
                                    }
                                }
                            }
                            out.newLine();
                            out.write("barline");
                        }
                    }
                }
            }
            out.close();
        }

        catch (ParserConfigurationException e) {e.printStackTrace();}
        catch (SAXException | IOException e) {e.printStackTrace();}
    }
}
