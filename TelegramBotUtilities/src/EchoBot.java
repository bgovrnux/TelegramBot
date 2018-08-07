import java.io.IOException;
import org.altervista.leocus.telegrambotutilities.*;
import com.rosaloves.bitlyj.Url;
import static com.rosaloves.bitlyj.Bitly.*;

//this bot using library from- https://leocus.altervista.org/telegram-bot-utilities-examples-send-simple-message/
public class EchoBot {


	public static void main(String[] args) throws GettingUpdatesException, IOException {
		
	    
		TelegramBot bot = new TelegramBot("682142454:AAFAuS9G8W7EF7ATfzv9zYDXsxY17peIQSU");
	
		Update[] updates1 = null;
		int updateCtr1 = -1;
		String[] query1 = {"נעם","משה"}, query2 ={"מוצר+שיתוף","מוצר+שיתוף ערוץ",
													"מוצר+שיתוף+שיתוף ערוץ 1", "מוצר+שיתוף+שיתוף ערוץ 2",
													"מוצר בלבד"};
		
		Message startMessage= null;
		String chatID = "", answer1="",answer2="", link="", text=""; 
		
		try{
			while(true){
				
				updates1 = bot.getUpdates(updateCtr1, -1, -1);
				if (updates1.length > 0){
					for (Update u1: updates1){
						startMessage = u1.getMessage();
						chatID = Integer.toString(startMessage.getFrom().getId());
											
						if(startMessage.getText().equals("/1")){
							answer1=OptionsGen(bot, query1);
							link = getLink(bot);
								if (link.equals("back"))break;
							
							if (answer1.equals("0")||answer1.equals("1")){
								answer2 = OptionsGen(bot, query2);
								link=(Url_ID_Generator(link , Integer.parseInt(answer1)));
								
								bot.sendMessage(chatID, LinksMaker(link, Integer.parseInt(answer2)));
							}
							
							if (answer1.equalsIgnoreCase("back")){
								break;
							}
						}
						if(startMessage.getText().equals("/2")){
							link = getLink(bot);
								if (link.equals("back"))break;
							text = getText(bot);
								if (link.equals("back"))break;
							bot.sendMessage(chatID, ShareLinkGenerator(link, text));
						}
						bot.sendMessage(chatID, " מה תרצה לעשות?");
						bot.sendMessage(chatID, "(בחר באפשרויות למטה)\n[בוט פרטי!]");
						updateCtr1 = updates1[updates1.length-1].getId() +1; //gets only the last message			
						
						if(startMessage.getText().equals("/3")){
							link = getLink(bot);
								if (link.equals("back"))break;
								bot.sendMessage(chatID, "הלינק המקוצר:");
							bot.sendMessage(chatID, "הלינק המקוצר-\n"+URL_Shortner(link));
						}
					}
					Thread.sleep(200);
				}
			}
		}catch (EmptyUpdatesException | GettingUpdatesException | IOException e){
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		
		

	}
	public static String OptionsGen(TelegramBot bot, String... arr) throws GettingUpdatesException, IOException{
		String line="";
		String[] numbers = new String[arr.length];
		for (int i=0; i<arr.length; i++ ){
			line=line+ "ל"+arr[i] +" שלח "+i+"\n";
			numbers[i]= Integer.toString(i);
		}
		
		Update[] updates =null;
		int updateCtr = bot.getCurrentUpdateId();
		Message liveMessage = null;
		String chatID = null;	
			try{
				while (true){
					updates = bot.getUpdates(updateCtr, -1, -1);
					

					if (updates.length > 0){
						for (Update u: updates){
							liveMessage = u.getMessage();
							chatID = Integer.toString(liveMessage.getFrom().getId());
							
							if (Check (numbers, liveMessage.getText())){
								bot.sendMessage(chatID, "בחרת- "+ arr[Integer.parseInt(liveMessage.getText())]);
								return liveMessage.getText();
							}
							if (liveMessage.getText().equals("אחורה")||liveMessage.getText().equals("/4")){
								return "back";
							}
														
							bot.sendMessage(chatID, " בחר:\n\n"+line+"\n(לחזרה אחורה שלח- 'אחורה')");
							updateCtr = updates[updates.length-1].getId() +1; 
						}
						
						Thread.sleep(200);
				}
					
			}

			
		}catch (EmptyUpdatesException | GettingUpdatesException | IOException e){
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "failed";
	}

	public static String getLink(TelegramBot bot) throws GettingUpdatesException, IOException{
		Update[] updates =null;
		int updateCtr = bot.getCurrentUpdateId();
		Message liveMessage = null;
		String chatID = null;	
			try{
				while (true){
					updates = bot.getUpdates(updateCtr, -1, -1);
					
					if (updates.length > 0){
						for (Update u: updates){
							liveMessage = u.getMessage();
							chatID = Integer.toString(liveMessage.getFrom().getId());
							
							if (liveMessage.getText().length()>15&& (liveMessage.getText().charAt(0)=='h'||liveMessage.getText().charAt(0)=='H')){
								bot.sendMessage(chatID, "שלחת- \n"+ liveMessage.getText());
								return liveMessage.getText();
							}
							if (liveMessage.getText().equals("אחורה")||liveMessage.getText().equals("/4")) return "back";
							bot.sendMessage(chatID, "שלח את הלינק\n\n(לחזרה שלח- 'אחורה'):");
							updateCtr = updates[updates.length-1].getId() +1; 
						}
						
						Thread.sleep(200);
				}
					
			}

			
		}catch (EmptyUpdatesException | GettingUpdatesException | IOException e){
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	return "eror";
	}
	public static String getText(TelegramBot bot) throws GettingUpdatesException, IOException{
		Update[] updates =null;
		int updateCtr = bot.getCurrentUpdateId();
		Message liveMessage = null;
		String chatID = null;	
			try{
				while (true){
					updates = bot.getUpdates(updateCtr, -1, -1);
					
					if (updates.length > 0){
						for (Update u: updates){
							liveMessage = u.getMessage();
							chatID = Integer.toString(liveMessage.getFrom().getId());
							
							
							if (liveMessage.getText().charAt(0)!='h'&&liveMessage.getText().charAt(0)!='H'){
								bot.sendMessage(chatID, "שלחת- \n"+ liveMessage.getText());
								return liveMessage.getText();
							}
							if (liveMessage.getText().equals("אחורה")||liveMessage.getText().equals("/4")) return "back";
							bot.sendMessage(chatID, "שלח את הטקסט (בשביל אנטר כתוב 00)\n\n(לחזרה שלח- 'אחורה'):");
							updateCtr = updates[updates.length-1].getId() +1; 
						}
						
						Thread.sleep(200);
				}
					
			}

			
		}catch (EmptyUpdatesException | GettingUpdatesException | IOException e){
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	return "eror";
	}
	public static boolean Check(String[] arr, String s){
		boolean ch=false;
		for (int i=0; i<arr.length; i++ ){
			if (arr[i].equals(s)) ch=true;
		}
		return ch;
	}
	public static String ShareLinkGenerator(String url, String text){
		
		text = text.replace(" ", "%20");
		text = text.replace("00", "%0a");
		
		return "https://telegram.me/share/url?url="+text+url;
		
	}
	public static String Url_ID_Generator(String url, int check){
	
		
		int index=url.lastIndexOf("#");
		
		if (index==-1&&!url.contains("zipy")){
			String fix=null;
			int i1 = url.indexOf("/");
			int i2 = url.indexOf("/", i1 + 1);
			int i3 = url.indexOf("/", i2 + 1);
			int i4 = url.indexOf("/", i3 + 1);
			int i5 = url.indexOf("/", i4 + 1);
			int i6 = url.indexOf(".", i5);
			int i7 = url.indexOf("?", i5);
			
			
			if (url.contains("aliexpress")){
				fix = url.substring(i5+1,i6);
				url="https://www.zipy.co.il/p/aliexpress/-/" + fix +"/#";
							
			}
			if (url.contains("ebay")){
				fix = url.substring(i5+1,i7);
				url="https://www.zipy.co.il/p/ebay/-/" + fix +"/#";
			}
		}
		
		if (check==0&&url.contains("zipy")){
			if (index!=-1) 
				url =url.substring(0,url.lastIndexOf("#")+1)+"2";
			else url=url+"#2";
		}
		
		if (check==1&&url.contains("zipy")){
			if (index!=-1)
				url =url.substring(0,url.lastIndexOf("#")+1)+"498508";
			else url=url+"#498508";
				
		}
		
		
		System.out.println(url);
		return URL_Shortner(url);
	}
	
	public static String LinksMaker(String url, int mode){//מסדר את הקישורים עם השמות של הכפתורים
		
		
		if (mode==0) {
			return("לשיתוף המוצר📢 - " +
					"https://telegram.me/share/url?url=היי!🤠%0aמצאתי%20מוצר%20שאני%20חושב%20שיעניין%20אותך👇%0a" + url +
					" | 🔥להזמנה🔥 - " + url);
		}
		if (mode==1) {
			return("לשיתוף הערוץ📢 - " +
					"https://telegram.me/share/url?url=https://t.me/israelshopping" +
					" | 🔥להזמנת המוצר🔥 - " + url);
		}
		if (mode==2) {
			return("לשיתוף המוצר📢 - " +
					"https://telegram.me/share/url?url=היי!🤠%0aמצאתי%20מוצר%20שאני%20חושב%20שיעניין%20אותך👇%0a" + url +
					" | 🔥להזמנת המוצר🔥 - " + url +
					"\nלשיתוף הערוץ🌐 - https://telegram.me/share/url?url=היי!🤠%0a%0aרציתי%20לשתף%20אותך%20בערוץ%20מגניב%20שמצאתי%20בטלגרם😍%0aיש%20בו%20מלא%20מוצרים%20זולים%20ואיכותיים🔥%0a%0aלהצטרפות👇%0ahttps://telegram.me/IsraelShopping");
		}
		if (mode==3) {
			return("🔥✅ ⬅️  להזמנה- לחצו כאן!! ➡️ ✅🔥 - " + url + 
					"\nלשיתוף הערוץ🌐 - https://telegram.me/share/url?url=היי!🤠%0a%0aרציתי%20לשתף%20אותך%20בערוץ%20מגניב%20שמצאתי%20בטלגרם😍%0aיש%20בו%20מלא%20מוצרים%20זולים%20ואיכותיים🔥%0a%0aלהצטרפות👇%0ahttps://telegram.me/IsraelShopping"+
					" | לשיתוף המוצר📢 - " + "https://telegram.me/share/url?url=היי!🤠%0aמצאתי%20מוצר%20שאני%20חושב%20שיעניין%20אותך👇%0a" + url);
		}
		if (mode==4){
			return("🔥✅ ⬅️  להזמנה- לחצו כאן!! ➡️ ✅🔥 - " + url);
		}
		return "eror";
	}
	public static void Buttons(TelegramBot b, String chatID, String answer, String link) throws IOException, GettingUpdatesException{
		//Doesn't work. can't answer with too long String (+has to be with equals column and lines in the array)
		link=(Url_ID_Generator(link , Integer.parseInt(answer)));
		InlineKeyboardButton[][] buttons = new InlineKeyboardButton[3][2];   
	    buttons[0][0] = new InlineKeyboardButton("מוצר+שיתוף", null, LinksMaker(link, 1), null);
	    buttons[0][1] = new InlineKeyboardButton("מוצר+שיתוף ערוץ", null, LinksMaker(link, 2), null);	
	    buttons[1][0] = new InlineKeyboardButton("מוצר+שיתוף+שיתוף ערוץ 1", null, LinksMaker(link, 3), null);
	    buttons[1][1] = new InlineKeyboardButton("מוצר+שיתוף+שיתוף ערוץ 2", null, LinksMaker(link, 4), null);
	    buttons[2][0] = new InlineKeyboardButton("רק מוצר", null, LinksMaker(link, 5), null);
	    buttons[2][1] = new InlineKeyboardButton("ריק", null, "empty", null);
		System.out.println("1");

	    InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup(buttons);	
		System.out.println("2");
		b.sendMessage(chatID, "בחר אפשרות:", "html", false, false, 0, inlineKeyboardMarkup);
	
	    /* Here you can handle the answer, in this case I gave 8 seconds to answer
	     * But it can be done also in a loop.</ul> */
	   try {	
		   	Thread.sleep(8000);	
	        Update[] u = b.getUpdates(0, 0, 0);	
	        Update up = u[u.length - 1];	
	        if (up.getCallbackQuery() != null) {	
	            b.sendMessage(chatID, up.getCallbackQuery().getData());	
	        }
	    } catch (EmptyUpdatesException e) {	
	        e.printStackTrace();	
	    } catch (InterruptedException e) {	
	        e.printStackTrace();
		    }
	}
	
	public static String URL_Shortner(String s){
		
		Url url = as("o_4ih6egvtbj", "R_e51cc670db8b49c28983cc9d0e1add4d").call(shorten(s));
	        System.out.println("Short success!");
	   return url.getShortUrl(); 
	}
}
