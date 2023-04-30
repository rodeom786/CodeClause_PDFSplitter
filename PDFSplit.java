// Created By : Om Anil Rode

package Split;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.*;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import java.awt.Font;
//done
//implements ActionListener
public class PDFSplit implements ActionListener
{
	
    private JButton Splitb,cancelb; 
    public PDFSplit()
    {

        //frame
        JFrame frame = new JFrame("PDF Splitter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        
        cancelb = new JButton("Cancel");
        cancelb.setBounds(152, 141, 73, 26);
        frame.getContentPane().add(cancelb);
        // Designing GUI
    	
        Splitb = new JButton("Select PDF File");
        Splitb.setBounds(126, 90, 121, 26);
        frame.getContentPane().add(Splitb);
        
        JLabel lblNewLabel = new JLabel("Split PDF File");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Arial", Font.BOLD, 14));
        lblNewLabel.setBounds(146, 12, 101, 14);
        frame.getContentPane().add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("<html>Separate one page or a whole set for easy conversion <br><center> into independent PDF files.</html>");
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 12));
        lblNewLabel_1.setBounds(0, 38, 373, 40);
        frame.getContentPane().add(lblNewLabel_1);
        Splitb.addActionListener(this);				//button performs specific action
        cancelb.addActionListener(this);			//button performs specific action
        frame.setSize(400, 350);  
       
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent event)		//buttons action is performed here
    { 
        
        if (event.getSource()==Splitb) 
        {
        	try 
        	{
        		JFileChooser filechoser = new JFileChooser();
        		filechoser.setDialogTitle("Select PDF File");
        		int result = filechoser.showOpenDialog(null);
        		if (result == JFileChooser.APPROVE_OPTION)
        		{
        			File selectedFile  = filechoser.getSelectedFile();
        			
        			//load the selected PDF file 
        			PDDocument document = PDDocument.load(selectedFile);
        			
        			// get the number of pages  
        			int numPages = document.getNumberOfPages();
				
        			// get the range of page to split from the user 
        			String range =JOptionPane.showInputDialog("Enter the range of pages to split (e.g. 1-3) : ","1"+numPages);
				
        			int startPage= Integer.parseInt(range.split( "-")[0].trim())-1;
        			int endPage = Integer.parseInt(range.split("-")[1].trim())-1;
				 
        			// split the PDF into single page documents 
        			for ( int i = startPage ; i<= endPage ; i++)
        			{
        				PDDocument singlePageDoc = new PDDocument();
        				PDPage page = document.getPage(i);
        				singlePageDoc.addPage(page);
        				singlePageDoc.save("output_"+ (i + 1)+".pdf");
        				singlePageDoc.close();
        			}
				//close the main document 
				document.close();
				JOptionPane.showMessageDialog(null,"PDF Splitted Successfully ");
				}
        	}
        	catch(IOException e)
        	{
        		e.printStackTrace();
        	}
        }
        else if (event.getSource()==cancelb) 
        {
        	System.exit(0);
        }
    }
    public static void main(String[] args) throws Exception 
	{
    	//create object instance
        PDFSplit p= new PDFSplit();
    }
}
