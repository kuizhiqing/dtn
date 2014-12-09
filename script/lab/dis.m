clear;clf;
% reality	sassy	haggle	pmtr	rollernet
ro = [678,794,682,558,404,238,116,84,42,26,42];%roller
sa = [102,0,28,6,12,8,10,12,8,4,20]; %sassy
pm = [834,298,168,102,46,24,20,16,8,4,2]; %pmtr
in = [1804,1576,1544,1264,942,508,338,228,140,120,89]; %infocom

subplot(2,2,1);plot(ro);
set(gca,'XTickLabel',{'1-10','11-20','21-30','31-40','41-50','51-60','61-70','71-80','81-90','91-100','100+'});
xlabel('rollernet 相遇次数区间'),ylabel('节点数') 
subplot(2,2,2);plot(sa);
set(gca,'XTickLabel',{'1-10','11-20','21-30','31-40','41-50','51-60','61-70','71-80','81-90','91-100','100+'});
xlabel('sassy 相遇次数区间'),ylabel('节点数') 
subplot(2,2,3);plot(pm);
set(gca,'XTickLabel',{'1-10','11-20','21-30','31-40','41-50','51-60','61-70','71-80','81-90','91-100','100+'});
xlabel('pmtr 相遇次数区间'),ylabel('节点数') 
subplot(2,2,4);plot(in);
set(gca,'XTickLabel',{'1-10','11-20','21-30','31-40','41-50','51-60','61-70','71-80','81-90','91-100','100+'});
xlabel('haggle 相遇次数区间'),ylabel('节点数') 