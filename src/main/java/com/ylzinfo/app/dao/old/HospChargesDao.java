package com.ylzinfo.app.dao.old;

import com.ylzinfo.app.model.old.ChargesQueryHelper;
import com.ylzinfo.app.model.old.HospCharges;

import java.util.ArrayList;


public interface HospChargesDao {

    public ArrayList<HospCharges> getHospChargesByCondition(ChargesQueryHelper chargesQueryHelper);

}
