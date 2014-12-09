clear;clf;
% reality	sassy	haggle	pmtr	rollernet
fratio = [39.45	250.08	4586.79	163.37	95383.82];
bar(log(fratio),0.5);
set(gca,'XTickLabel',{'reality','sassy','haggle','pmtr','rollernet'});
xlabel('数据集'),ylabel('相遇频率指标') 
