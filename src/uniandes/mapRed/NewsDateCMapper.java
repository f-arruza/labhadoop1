package uniandes.mapRed;

import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class NewsDateCMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
	
    @Override
    protected void map(LongWritable key, Text value,
                    Context context)
                    throws IOException, InterruptedException {
        
        Pattern p = Pattern.compile("(<DATE>)");
        Matcher m = p.matcher(value.toString());
        HashMap<String, Integer> places = new HashMap<>();

        if(m.find()) {            
            String line = value.toString();
            if(!line.isEmpty()) {
                line = line.substring(6, (!line.contains("</DATE>"))? line.length() : line.indexOf("</DATE>"));
                
                String[] places_array = line.split("<D>");
                for(String place : places_array) {
                    if(!place.trim().isEmpty()) {
                        place = place.substring(0, (!place.contains("</D>"))? place.length() : place.indexOf("</D>"));                        
                        places.put(place, places.containsKey(places)? (places.get(place) + 1) : 1);
                    }                
                }
                
                for(String k : places.keySet()){
                    context.write(new Text(k), new IntWritable(places.get(k)));
		}                
            }
        }
    }
}
