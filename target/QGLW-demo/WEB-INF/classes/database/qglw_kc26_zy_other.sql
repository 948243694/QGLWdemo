create or replace view vw_QGLW_KC26 as
select
AAB299,AAB301,AKC264,AKE149,
AKC194,decode(BAE009,'-','-1','1') AAA113,
AKC190,AAZ216,AAZ496 as AAZ497,AAZ498,AKB067
from Qglw_Kc26_Zy ;

alter table qglw_kc26_zy drop column AAE314;




