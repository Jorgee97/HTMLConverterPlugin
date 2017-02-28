package ingsoftware;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class text {
	
	public static void main(String[] args){
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setText("Plugin Para Convertir texto plano con formato a HTML");
		
		RowLayout rowLayout = new RowLayout();
		rowLayout.marginLeft = 10;
		rowLayout.marginTop = 10;
		rowLayout.spacing = 15;
		shell.setLayout(rowLayout);
		
		Label label = new Label(shell, SWT.NONE);
		label.setText("Escriba su head aquí: ");
		
		Text text = new Text(shell, SWT.MULTI | SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
		RowData layoutData = new RowData();
		layoutData.width = 600;
		layoutData.height = 100;
		text.setLayoutData(layoutData);
		
		Label label1 = new Label(shell, SWT.NONE);
		label1.setText("Escriba su body aquí: ");
		
		Text text1 = new Text(shell, SWT.MULTI | SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
		RowData layoutData1 = new RowData();
		layoutData1.width = 600;
		layoutData1.height = 100;
		text1.setLayoutData(layoutData1);
		
		Button convert = new Button(shell, SWT.PUSH);
		convert.setText("Convertir a html");
		
		Text text2 = new Text(shell, SWT.MULTI | SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
		RowData layoutData2 = new RowData();
		layoutData2.width = 600;
		layoutData2.height = 200;
		text2.setLayoutData(layoutData2);
		
		convert.addSelectionListener(new SelectionAdapter(){
			@Override
			public void widgetSelected(SelectionEvent e){
				String head = text.getText();
				String []head2 = head.split("\n");
				ArrayList<String> scripts = new ArrayList<String>();
				ArrayList<String> styles = new ArrayList<String>();
				
				String body = text1.getText();
				String []body2 = body.split("\n");
				ArrayList<String> h1 = new ArrayList<String>();
				ArrayList<String> p = new ArrayList<String>();
				ArrayList<String> a = new ArrayList<String>();
				ArrayList<String> form = new ArrayList<String>();
				ArrayList<String> img = new ArrayList<String>();
				
				for(int i = 0; i < head2.length; i++){
					String []head3 = head2[i].split("\\|");
					if(head3[0].contains("script")){
						scripts.add("<script src='"+head3[1]+"'></script>");
					}
					if(head3[0].contains("css")){
						styles.add("<link rel='stylesheet' type='text/css' href='"+head3[1]+"'>");
					}
				}
				
				for(int i = 0; i < body2.length; i++){
					String []body3 = body2[i].split("\\|");
					if(body3[0].contains("h1"))
						h1.add("<h1>"+ body3[1] +"</h1>");
					if(body3[0].contains("p"))
						p.add("<p>" + body3[1] + "</p>");
					if(body3[0].contains("a")){
						String []separate = body3[1].split("\\-"); 
						a.add("<a href='" + separate[0] + "'>"+ separate[1] +"</a>");
					}
					if(body3[0].contains("form"))
						form.add("<form method='" + body3[1] + "'></form>");
					if(body3[0].contains("img"))
						img.add("<img src='" + body3[1] + "'>");
				}

				StringBuffer scriptsOutput = new StringBuffer(110);
				StringBuffer stylesOutput = new StringBuffer(110);
				StringBuffer h1Output = new StringBuffer(110);
				StringBuffer pOutput = new StringBuffer(110);
				StringBuffer aOutput = new StringBuffer(110);
				StringBuffer formOutput = new StringBuffer(110);
				StringBuffer imgOutput = new StringBuffer(110);
				
				for(int i = 0; i < scripts.size(); i++)
					scriptsOutput.append(scripts.get(i) + "\n");
				
				for(int i = 0; i < styles.size(); i++)
					stylesOutput.append(styles.get(i) + "\n");
				
				for(int i = 0; i < h1.size(); i++)
					h1Output.append(h1.get(i) + "\n");
				
				for(int i = 0; i < p.size(); i++)
					pOutput.append(p.get(i) + "\n");
				
				for(int i = 0; i < a.size(); i++)
					aOutput.append(a.get(i) + "\n");
				
				for(int i = 0; i < form.size(); i++)
					formOutput.append(form.get(i) + "\n");
				
				for(int i = 0; i < img.size(); i++)
					imgOutput.append(img.get(i) + "\n");
				
				text2.setText("<!DOCTYPE html>\n"
						+ "<html>\n"
						+ "<head>\n"
						+ scriptsOutput
						+ stylesOutput
						+ "</head>\n"
						+ "<body>\n"
						+ h1Output
						+ pOutput
						+ aOutput
						+ formOutput
						+ imgOutput
						+ "</body>\n"
						+ "</html>");
			}
		});
		
		shell.setSize(700, 650);
		shell.open();
		while(!shell.isDisposed()){
			if(!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}
	
}
