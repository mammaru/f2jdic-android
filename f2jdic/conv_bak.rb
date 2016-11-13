# encoding: utf-8

require 'csv'

csv_data = CSV.read('./data/utf8/frU.csv', headers: true)

#print "Start..."
i = 1
j = 1
File.open("./data/utf8/idiom.csv", 'w') do |idiom|
  #idiom.write("label,trans,exp,level,memory,modify,pron,filelink\n")
  #idiom.write("_id,label,trans,exp,level,memory,modify,pron,filelink\n")
  #idiom.write("_id,label,trans,exp,count,modified\n")
  File.open("./data/utf8/word.csv", 'w') do |word|
    #word.write("_id,label,trans,exp,level,memory,modify,pron,filelink\n")
    #word.write("_id,label,trans,exp,count,modified\n")
    csv_data.each do |data|
      if data["word"].include?(" ")
        #idiom.write(i.to_s+","+data.to_s)
        idiom.write("#{i},\"#{data["word"].gsub('"', '""')}\",\"#{data["trans"].gsub('"', '""')}\",\"#{data["exp"].gsub('"', '""')}\",0,0\n")
        #idiom.write("#{i},\'#{data["word"]}\',\'#{data["trans"]}\',\'#{data["exp"]}\',0,0\n")
        #idiom.write([i].concat(data.to_s.split(",",-1)[0..2]).concat([0,0]).join(",").delete("\"")+"\n")
        i+=1
      else
        #word.write(j.to_s+","+data.to_s)
        word.write("#{j},\"#{data["word"].gsub('"', '""')}\",\"#{data["trans"].gsub('"', '""')}\",\"#{data["exp"].gsub('"', '""')}\",0,0\n")
        #word.write("#{j},\'#{data["word"]}\',\'#{data["trans"]}\',\'#{data["exp"]}\',0,0\n")
        #word.write([j].concat(data.to_s.split(",",-1)[0..2]).concat([0,0]).join(",").delete("\"")+"\n")
        #word.write([j].concat(data.to_s.split(",",-1)[0..2]).concat([0,0]).join(",").delete("\"")+"\n")
        j+=1
      end
    end
  end
end

#puts "done!"
