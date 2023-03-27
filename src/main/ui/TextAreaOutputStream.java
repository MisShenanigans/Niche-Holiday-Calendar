package ui;

import java.io.IOException;
import java.io.OutputStream;

import javax.swing.JTextArea;

/**
 * TextAreaOutputStream creates an outputstream that will output to the
 * given textarea. Useful in setting System.out
 */
public class TextAreaOutputStream extends OutputStream {
    public static final int DEFAULT_BUFFER_SIZE = 1;

    JTextArea myText;
    byte myBuf[];
    int myLocation;

    public TextAreaOutputStream(JTextArea component) {
        this(component, DEFAULT_BUFFER_SIZE);
    }

    public TextAreaOutputStream(JTextArea component, int bufferSize) {
        myText = component;
        if (bufferSize < 1) bufferSize = 1;
        myBuf = new byte[bufferSize];
        myLocation = 0;
    }

    @Override
    public void write(int arg0) throws IOException {
        //System.err.println("arg = "  + (char) arg0);
        myBuf[myLocation++] = (byte)arg0;
        if (myLocation == myBuf.length) {
            flush();
        }
    }

    public void flush() {
        myText.append(new String(myBuf, 0, myLocation));
        myLocation = 0;
/*
		try {
			Thread.sleep(1);
		}
		catch (Exception ex) {}
	}
	*/
    }
}
