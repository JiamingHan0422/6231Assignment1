package recordFile;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;


public class LogEnteyBuffer extends OutputStream {
    private List<OutputStream> d_OutputStreams;

    public LogEnteyBuffer (List<OutputStream> d_OutputStreams){
        this.d_OutputStreams = d_OutputStreams;
    }

    @Override
    public void write(int recordCount) throws IOException {
        for(OutputStream OS : d_OutputStreams)
            OS.write(recordCount);
    }

    @Override
    public void flush() throws IOException {
        for(OutputStream OS : d_OutputStreams){
            OS.flush();
        }
    }

    @Override
    public void close() throws IOException {
        for(OutputStream OS : d_OutputStreams){
            OS.close();
        }
    }
}
