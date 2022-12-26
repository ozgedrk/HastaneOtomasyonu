package HastaneOtomasyon;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JFormattedTextField.AbstractFormatter;

public interface Formatter {

	final String datePattern = "yyyy-MM-dd";
	final SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);
	public DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy kk:mm");

	public Object stringToValue(String text) throws ParseException;

	public String valueToString(Object value) throws ParseException;

}