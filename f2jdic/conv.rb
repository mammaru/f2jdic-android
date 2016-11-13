# encoding: utf-8

require 'csv'

csv_data = CSV.read('./data/utf8/frU.csv', headers: true)

i = 1
j = 1
File.open("./data/utf8/idiom.csv", 'w') do |idiom|
  File.open("./data/utf8/word.csv", 'w') do |word|
    csv_data.each do |data|
      trans_str =  data["trans"].gsub('"', '""').to_s
      if en = trans_str.match(/\[([\w,\!\-\s\(\)]+)\]/)
        #puts en[0].gsub(/-/,'\\-')
        trans_jp = '"' + trans_str.delete(en[0].gsub(/-/,'\\-')) + '"'
        trans_en = '"' + en[1] + '"'
      else
        trans_jp = '"' + trans_str + '"'
        trans_en = '""'
      end
      trans_str = trans_jp + ',' + trans_en
      #puts trans_str
      if data["word"].include?(" ")
        #idiom.write("#{i},\"#{data["word"].gsub('"', '""')}\",\"#{data["trans"].gsub('"', '""')}\",\"#{data["exp"].gsub('"', '""')}\",0,0\n")
        idiom.write("#{i},\"#{data["word"].gsub('"', '""')}\",#{trans_str},\"#{data["exp"].gsub('"', '""')}\",0,0\n")
        i+=1
      else
        #word.write("#{j},\"#{data["word"].gsub('"', '""')}\",\"#{data["trans"].gsub('"', '""')}\",\"#{data["exp"].gsub('"', '""')}\",0,0\n")
        word.write("#{j},\"#{data["word"].gsub('"', '""')}\",#{trans_str},\"#{data["exp"].gsub('"', '""')}\",0,0\n")
        j+=1
      end
    end
  end
end

