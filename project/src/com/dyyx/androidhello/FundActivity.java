package com.dyyx.androidhello;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dyyx.androidhello.base.BaseActivity;
import com.dyyx.androidhello.util.DBUtil;
import com.dyyx.androidhello.util.DyyxCommUtil;
import com.dyyx.androidhello.util.HelloConst;

public class FundActivity extends BaseActivity {

	
	EditText textEditDate = null;
	Button btnRead = null;
	Button btnReport = null;
	Button btnSave = null;
	Button btnQuery = null;
	
	
	EditText textEditHs300 = null;
	EditText textEditSab = null;
	EditText textEditMfb = null;
	EditText textEditSmv = null;
	EditText textEditCv = null;
	EditText textEditBv = null;
	EditText textEditSv = null;
	EditText textEditReport = null;
	EditText textEditBuys = null;
	EditText textEditSells = null;
	
	private static final String TRADE_DATA_FORMAT_ERROR = "trade_data_format_error";
	private static final String REPORT_SEP = "|";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fund);

		
		btnRead = (Button) findViewById(R.id.btnRead);
		
		
		textEditDate = (EditText) findViewById(R.id.textEditDate);
		
		textEditHs300 = (EditText) findViewById(R.id.textEditHs300);
		textEditSab = (EditText) findViewById(R.id.textEditSab);
		textEditMfb = (EditText) findViewById(R.id.textEditMfb);
		textEditSmv = (EditText) findViewById(R.id.textEditSmv);
		textEditCv = (EditText) findViewById(R.id.textEditCv);
		textEditBv = (EditText) findViewById(R.id.textEditBv);
		textEditSv = (EditText) findViewById(R.id.textEditSv);
		textEditReport = (EditText) findViewById(R.id.textEditReport);
		
		textEditBuys = (EditText) findViewById(R.id.textEditBuys);
		textEditSells = (EditText) findViewById(R.id.textEditSells);
		
		
		
		Date now = new Date();
		String fundDateNow = DyyxCommUtil.getDateString(now, DyyxCommUtil.FUND_DATE_FORMAT);
		
		textEditDate.setText(fundDateNow);
		
		
		
		
	}

	public void clickHandle(View view) {
		int vid = view.getId();
		if (vid == R.id.btnRead) {


			String date = textEditDate.getText().toString();
			
			Date dateTime = DyyxCommUtil.getDate(date, DyyxCommUtil.FUND_DATE_FORMAT,null);
			if(dateTime==null){
				//throw new RuntimeException("date error,date="+date);
				String msg = "date error,date="+date;
				Toast.makeText(FundActivity.this, msg,Toast.LENGTH_SHORT).show(); 
				return;
			}
			
			FundDTO fund = getFundByDate(date);
			if(fund==null){
				String msg = "fund not exist,date="+date;
				Toast.makeText(FundActivity.this, msg,Toast.LENGTH_SHORT).show(); 
				
				updateView(fund);
				
				return;
			}
		
			updateView(fund);

			return;
		}

		if (vid == R.id.btnReport) {
			
			FundDTO dto = null;
			
            try{
               dto = getFundDTO();
            }catch(Throwable e){
            	 Toast.makeText(FundActivity.this, e+"",Toast.LENGTH_SHORT).show(); 
            	 return;
            }
			
            textEditBv.setText(dto.bv + "");
        	textEditSv.setText(dto.sv + "");
        	textEditReport.setText(dto.report);
        	textEditBuys.setText(dto.buys);
        	textEditSells.setText(dto.sells);
            
		
			return;
		}
		
		if (vid == R.id.btnSave) {
			
			
            FundDTO dto = null;
			
            try{
               dto = getFundDTO();
            }catch(Throwable e){
            	 Toast.makeText(FundActivity.this, e+"",Toast.LENGTH_SHORT).show(); 
            	 return;
            }
            
            insertOrUpdateFund(dto);
            Toast.makeText(FundActivity.this, "save done",Toast.LENGTH_SHORT).show(); 
			
			return;
		}
		
		if (vid == R.id.btnQuery) {
            
			
			return;
		}

	}
	
	
	private static class TradeDTO{
		public double price;
		public long volume;
		
		@Override
		public String toString(){
			return price+HelloConst.SPACE+volume;
		}
		
	}
	
	private static TradeDTO getTradeDTO(String str){
		if(DyyxCommUtil.isBlank(str)){
			return null;
		}
		
		str = str.trim();
		int pos = str.indexOf(HelloConst.SPACE);
		if(pos<=0){
			return null;
		}
		// 2.201 7000
		String s1 = str.substring(0,pos);
		
		double price = DyyxCommUtil.getDouble(s1, 0);
		
		if(price<=0){
			return null;
		}
		
		String s2 = str.substring(pos+HelloConst.SPACE.length());
		
		long volume = DyyxCommUtil.getLong(s2, 0);
		if(volume<=0){
			return null;
		}
		TradeDTO dto = new TradeDTO();
		dto.price = price;
		dto.volume = volume;
		
		return dto;
	}
	
	private static String getTradeString(List<TradeDTO> list){
		if(list==null || list.isEmpty()){
			return null;
		}
		StringBuilder sb = new StringBuilder();
		boolean isfirst = true;
		
		for(TradeDTO dto:list){
			if(dto==null){
				continue;
			}
			if(isfirst){
				isfirst=false;
			}else{
				sb.append(HelloConst.LINE_SEP);
			}
			sb.append(dto);
		}
		
		
		return sb.toString();
	}
	
	private static List<TradeDTO> getTradeDTOs(String str){
		if(DyyxCommUtil.isBlank(str)){
			return null;
		}
		List<String> lines = DyyxCommUtil.split(str, HelloConst.LINE_SEP);
		if(lines==null || lines.isEmpty()){
			return null;
		}
		TradeDTO dto = null;
		List<TradeDTO> list = new ArrayList<TradeDTO>();
		for(String line:lines){
			 if(DyyxCommUtil.isBlank(line)){
				 continue;
			 }
			 dto = getTradeDTO(line);
			 if(dto==null){
				 throw new RuntimeException(TRADE_DATA_FORMAT_ERROR+","+line);
			 }
			 list.add(dto);		 
		}
		return list;
	}
	
	private static int getAmount(List<TradeDTO> list){
		if(list==null || list.isEmpty()){
			return 0;
		}
		double sum = 0;
		for(TradeDTO dto:list){
			sum = sum + dto.price * dto.volume;
		}
		return new Double(sum).intValue();
	}
	
	
	
	private FundDTO getFundDTO(){
		FundDTO dto = new FundDTO();
		
		String date = textEditDate.getText().toString();
		
		Date dateTime = DyyxCommUtil.getDate(date, DyyxCommUtil.FUND_DATE_FORMAT,null);
		if(dateTime==null){
			throw new RuntimeException("date error,date="+date);
		}
		
		dto.date = date;
		
		double hs300index = DyyxCommUtil.getDouble(textEditHs300.getText().toString(), 0);
		
		if(hs300index<=1000){
			throw new RuntimeException("hs300index error");
		}
		
		dto.hs300index = hs300index;
		
		int sab = DyyxCommUtil.getInt(textEditSab.getText().toString(), -1);
		
		if(sab<0){
			throw new RuntimeException("stock account balance error");
		}
		
		dto.sab = sab;
		
		int mfb = DyyxCommUtil.getInt(textEditMfb.getText().toString(), -1);
		
		if(mfb<0){
			throw new RuntimeException("money fund balance error");
		}
		
		dto.mfb = mfb;
		
		
		int smv = DyyxCommUtil.getInt(textEditSmv.getText().toString(), -1);
		
		if(smv<0){
			throw new RuntimeException("stock market value error");
		}
		
		dto.smv = smv;
		
		Integer cvobj = DyyxCommUtil.getIntObj(textEditCv.getText().toString(),null);
		
		if(cvobj==null){
			throw new RuntimeException("corrected value error");
		}
		
		int cv = cvobj.intValue();
		
		dto.cv = cv;
		
		
		int bv = DyyxCommUtil.getInt(textEditBv.getText().toString(), -1);
		int sv = DyyxCommUtil.getInt(textEditSv.getText().toString(), -1);
		
		if(bv<0){
			throw new RuntimeException("buy volume error");
		}
		
		if(sv<0){
			throw new RuntimeException("sell volume error");
		}
		
		dto.bv = bv;
		dto.sv = sv;
		
		
		String buys = textEditBuys.getText().toString();
		String sells = textEditSells.getText().toString();
	
		dto.buys = buys;
		dto.sells = sells;
		
		dto.build();
		
		
		
		return dto;
	}
	
	
	private class FundDTO{
		
		public int id=0;
		
		public String date;
		
		
		public double hs300index;
		public int sab;
		public int mfb;
		public int smv;
		public int cv;
		public int bv;
		public int sv;
		public int total;
		
		public String report;
		
		public String buys;
		public String sells;
		
		public List<TradeDTO> buyTrades;
		public List<TradeDTO> sellTrades;
		
		
		
		public void build(){
			
			buyTrades =  getTradeDTOs(buys);
			sellTrades =  getTradeDTOs(sells);
			
			int bvtmp = getAmount(buyTrades);
			
			int svtmp = getAmount(sellTrades);
			
			if(bvtmp>0 && bv<=0){
				bv = bvtmp;
			}
			if(svtmp>0 && sv<=0){
				sv = svtmp;
			}
			total = sab + mfb + cv;
			
			
			buildReport();
			
			this.buys = getTradeString(buyTrades);
			this.sells = getTradeString(sellTrades);
			
		}
		
		private void buildReport(){
			String s = date+REPORT_SEP+total+REPORT_SEP+hs300index+REPORT_SEP+smv+REPORT_SEP+bv+REPORT_SEP+sv;
			report = s;
		}
		
		@Override
		public String toString(){
			StringBuilder sb = new StringBuilder();
			sb.append("id="+id);
			sb.append(",date="+date);
			sb.append(",hs300index="+hs300index);
			sb.append(",sab="+sab);
			sb.append(",mfb="+mfb);
			sb.append(",smv="+smv);
			sb.append(",cv="+cv);
			sb.append(",total="+total);
			sb.append(",bv="+bv);
			sb.append(",sv="+sv);
			sb.append(",report="+report);
			
			sb.append(",buys="+buys);
			sb.append(",sells="+sells);
			
			return sb.toString();		
		}
		
	}
	
	
	private int insertFund(FundDTO fund){
		String sql = "insert into fund(report_date,hs300index,sab,mfb,smv,cv,bv,sv,report,buys,sells) ";
		sql=sql+"values(?,?,?,?,?,?,?,?,?,?,?)";
			
		Object[]params = new Object[11];
		
		params[0] = fund.date;
		params[1] = fund.hs300index;
		
		params[2] = fund.sab;
		params[3] = fund.mfb;
		params[4] = fund.smv;
		params[5] = fund.cv;
		params[6] = fund.bv;
		params[7] = fund.sv;
		params[8] = fund.report;
		params[9] = fund.buys;
		params[10] = fund.sells;
		
		DBUtil.executeSql(sql, params);
		
		return 0;
	}
	
	
    
	
	
	private int updateFund(FundDTO fund){
		int id = fund.id;
		if(id<=0){
			throw new RuntimeException("id error,id="+id);
		}
		String sql = "update fund set hs300index=?,sab=?,mfb=?,smv=?,cv=?,bv=?,sv=?,report=?,buys=?,sells=? where id=?";
		// 11
		
		Object[]params = new Object[11];
		
		//params[0] = fund.date;
		params[0] = fund.hs300index;
		
		params[1] = fund.sab;
		params[2] = fund.mfb;
		params[3] = fund.smv;
		params[4] = fund.cv;
		params[5] = fund.bv;
		params[6] = fund.sv;
		params[7] = fund.report;
		params[8] = fund.buys;
		params[9] = fund.sells;
		params[10] = fund.id;
		
		DBUtil.executeSql(sql, params);
		
		return 0;
	}
	
	private int insertOrUpdateFund(FundDTO fund){
		String date = fund.date;
		if(DyyxCommUtil.isBlank(date)){
			throw new RuntimeException("date is blank");
		}
		FundDTO f = getFundByDate(date);
		if(f==null){
			insertFund(fund);
		}else{
			fund.id=f.id;
			updateFund(fund);
		}
		
		return 0;
	}
	
	private FundDTO getFundById(int id){
		if(id<=0){
			return null;
		}
		String sql = "select * from fund where id="+id;
		
		List list = DBUtil.query(sql, null,null);
		if(list==null || list.isEmpty()){
			return null;
		}
		Map<String,String> m = (Map<String,String>)list.get(0);
		return getFundDTO(m);
	}
	
	private FundDTO getFundByDate(String date){
		if(DyyxCommUtil.isBlank(date)){
			return null;
		}
		String sql = "select * from fund where report_date=?";
		String[]params = new String[1];
		params[0] = date;
		List list = DBUtil.query(sql, params,null);
		if(list==null || list.isEmpty()){
			return null;
		}
		Map<String,String> m = (Map<String,String>)list.get(0);
		return getFundDTO(m);
	}
	
	private FundDTO getFundDTO(Map<String,String> m){
		if(m==null){
			return null;
		}
		FundDTO fund = new FundDTO();
		String idstr = m.get("id");
		int id = DyyxCommUtil.getInt(idstr, 0);
		if(id<=0){
			throw new RuntimeException("id error,id="+idstr);
			
		}
		fund.id = id;
		String hs300indexstr = m.get("hs300index");
		double hs300index = DyyxCommUtil.getDouble(hs300indexstr, 0);
		if(hs300index<=0){
			throw new RuntimeException("hs300index error,hs300index="+hs300indexstr);	
		}
		fund.hs300index = hs300index;
		
		fund.sab = DyyxCommUtil.getInt(m.get("sab"), 0);
		fund.mfb = DyyxCommUtil.getInt(m.get("mfb"), 0);
		fund.smv = DyyxCommUtil.getInt(m.get("smv"), 0);
		fund.cv = DyyxCommUtil.getInt(m.get("cv"), 0);
		fund.bv = DyyxCommUtil.getInt(m.get("bv"), 0);
		fund.sv = DyyxCommUtil.getInt(m.get("sv"), 0);
		fund.report = m.get("report");
		fund.buys = m.get("buys");
		fund.sells = m.get("sells");
		
				
		return fund;
	}
	
	private void updateView(FundDTO fund){
		if(fund==null){
			fund = new FundDTO();
		}
		
		textEditHs300.setText(fund.hs300index+"");		
		textEditSab.setText(fund.sab+"");
		
		textEditMfb.setText(fund.mfb+"");
		
		textEditSmv.setText(fund.smv+"");
		
		textEditCv.setText(fund.cv+"");
		
		textEditBv.setText(fund.bv+"");
		textEditSv.setText(fund.sv+"");
		
		String buys = fund.buys;
		if(buys==null){
			buys="";
		}
		String sells = fund.sells;
		String report = fund.report;
		if(sells==null){
			sells="";
		}
		if(report==null){
			report="";
		}
		
		
		textEditBuys.setText(buys);
		textEditSells.setText(sells);
	 	textEditReport.setText(report);
		
		
	}

}
