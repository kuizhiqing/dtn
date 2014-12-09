clear;clf;
% reality	sassy	haggle	pmtr	rollernet
fratio = [0.672743,0.496,0.9192,0.786157,0.96774];
bar(fratio,0.5);
set(gca,'XTickLabel',{'reality','sassy','haggle','pmtr','rollernet'});
xlabel('数据集'),ylabel('比率') 
