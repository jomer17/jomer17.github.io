import java.io.*;
import java.util.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.util.ArrayList;
import java.util.List;


public class firsttry extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final int[] HURUF_DAY    = {1,2,400,500,3,8,600,4,700,200,7,60,300,90,800,9,900,70,1000,80,100,20,30,40,50,5,6,10};
    private static final int[] HURUF_NIGHT  = {1,2,400,500,3,8,600,4,700,200,7,300,1000,60,90,9,800,70,900,80,100,20,30,40,50,5,6,10};
	private static final int[] NUMBERS = {66,298,258,202,91,409,213,302,46,350,67,314,150,78,141,137,86,180,94,37,
	19,292,521,318,1286,312,340,88,812,18,156,110,1020,1060,62,14,114,181,351,319,86,1616,66,312,80,232,156,
	550,171,290,306,305,108,731,129,116,998,55,57,20,306,989,551,761,744,1281,90,20,256,68,489,526,111,1281,751,360,
	308,817,500,202,100,1038,37,801,1106,61,170,131,136,145,206,662,336,1020,270,606,261,13,134,1291};
    private static final String[] NAMES = {"Allah","Er-Rahman","Er-Rahim","Er-Rabb","El-Malik","Et-Tevvab","El-Bari`","El-Besir","El-Velijj",
	"En-Nesir","El-Muhit","El-Kadir","El-`Alîm","El-Hakim","El-`Âlim","El-Vasi`","El-Bedi`","Es-Semi`","El-`Aziz","El-Ilah",
	"El-Vahid","Er-Re`uf","Eš-Šakir","Eš-Šedid","El-Gafur","El-Karib","Es-Seri`","El-Halim","El-Habir","El-Hajj","El-Kajjum","El-`Alijj",
	"El-`Azim","El-Ganijj","El-Hamid","El-Vehhab","El-Džami`","Maliku-l-mulk","ER-Rafi`","Eš-Šehid","El-Mevla","Zu-l-Fadl","El-Vekil",
	"Er-Rekib","El-Hasib","El-Kebir","El-`Afuvv",
	"El-Mukit","El-`Allam","El-Fatir","El-Kahir","El-Kadir","El-Hakk","El-Halik","El-Latif","El-Kavijj","El-Hafîz","El-Mudžib","El-Medžid","El-Vedud",
	"El-Kahhar","El-Hâfiz","El-Mute`ali","El-Hallak","El-Muktedir","El-Gaffar","El-Melik","El-Hadi","En-Nur","El-Muhji","El-Fettah","Eš-Šekur",
	"El-Kafi","El-Gafir","Zu-t-Tavl","Er-Refi`",
	"Er-Rezzak","Zu-l-Kuvve","El-Metin","El-Berr","El-Melik","Zu-l-Dželali ve-l-Ikram","El-Evvel","El-Ahir","Ez-Zahir","El-Batin","El-Kuddus",
	"Es-Selam","El-Mu`min","El-Muhejmin","El-Džebbar","El_mutekebbir","El-Musavvir","Zu-l-Me`aridž","El-Kerim","El-Vitr","El-Ekrem","El-Ehad",
	"Es-Samed","Lem jelid ve lem juled ve lem jekun lehu kufuven ehad"};
    private static List<List<Integer>> result2 = new ArrayList<>();
    private static List<Integer> currentCombination2 = new ArrayList<>();
	
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
	ResourceBundle rb = ResourceBundle.getBundle("LocalStrings",request.getLocale());
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        try {
        out.println("<!DOCTYPE html><html>");
        out.println("<head>");
        out.println("<meta charset=\"UTF-8\" />");
        out.println("<style>");     // start style
		out.println(".crbr {");     // note leading brace
		out.println("font-size:24px;");
		out.println("background-color:#FF7A59;");
		out.println("color:white;");
		out.println("}");          // note trailing brace for h1 style
		out.println(".cright {");     
		out.println("font-size:10px;");
		out.println("text-align:right;");
		out.println("}"); 
		out.println(".ctbb {");     
		out.println("font-size:21px;");
		out.println("background-color:#FF7A59;");
		out.println("color:white;");
		out.println("}"); 
		out.println("</style>");  // terminate style
		
		String title = rb.getString("firsttry.title");
		String title2 = rb.getString("firsttry.title2");

        out.println("<title>" + title + "</title>");
        out.println("</head>");
        out.println("<body bgcolor=\"white\">");
        out.println("<h2>" + title + "</h2>");
		out.println("<p><b>UPUTSTVO:</b> Identifikujte slova/harfove u svom imenu. Odaberite pripadajuće redne brojeve i upišite ih redom <em>odvojene");
        out.println("razmakom. </em><br>Odaberite da li se računa za dan (1) ili noć (2), jer polje <em> Vrijednost harfa </em> za neke harfove ima po dvije vrijednosti, tj.");
        out.println("za dan ili noć. <br>Upišite do kojeg maksimalnog broja Allahovih imena u kombinaciji se računaju kombinacije. Npr. do 2, 3 ili više. </p>");
		out.println("<table border=1 style=\"");
		out.println("text-align: center\">  <tr><td colspan=16 class=ctbb><a>" + title2 + "</a></td></tr><tr><th>Redni broj</th><th>Harf</th><th>Naziv</th><th>Vrijednost harfa</th><th>Redni broj</th><th>Harf</th><th>Naziv</th><th>Vrijednost harfa</th>");
		out.println("<th>Redni broj</th><th>Harf</th><th>Naziv</th><th>Vrijednost harfa</th><th>Redni broj</th><th>Harf</th><th>Naziv</th><th>Vrijednost harfa</th></tr>");
        out.println("<tr><td class=crbr>1.</td><td style=font-size:32px>ا</td><td>Elif</td><td>1</td><td class=crbr>2.</td><td style=font-size:32px>ب</td><td>Ba</td><td>2</td><td class=crbr>3.</td><td style=font-size:32px>ت</td><td>Ta</td><td>400</td><td class=crbr>4.</td><td style=font-size:32px>ث </td><td>Sa</td><td>500</td></tr>");
        out.println("<tr><td class=crbr>5.</td><td style=font-size:32px>ج</td><td>Džim</td><td>3</td><td class=crbr>6.</td><td style=font-size:32px>ح</td><td>Ha</td><td>8</td><td class=crbr>7.</td><td style=font-size:32px>خ</td><td>Ha</td><td>600</td><td class=crbr> 8.</td><td style=font-size:32px> د </td><td>Dal</td><td>4</td></tr>");
        out.println("<tr><td class=crbr>9.</td><td style=font-size:32px>ذ</td><td>Zal</td><td>700</td><td class=crbr>10.</td><td style=font-size:32px>ر</td><td>Ra</td><td>200</td><td class=crbr>11.</td><td style=font-size:32px>ز</td><td>Za</td><td>7</td><td class=crbr>12.</td><td style=font-size:32px> س </td><td>Sin</td><td> 60 | 300</td></tr>");
        out.println("<tr><td class=crbr>13.</td><td style=font-size:32px>ش</td><td>Šin</td><td>300 | 1000</td><td class=crbr>14.</td><td style=font-size:32px>ص</td><td>Sad</td><td>90 | 60</td><td class=crbr>15.</td><td style=font-size:32px> ض </td><td>Dad</td><td>800 | 90</td><td class=crbr>16.</td><td style=font-size:32px> ط </td><td>Ta</td><td>9</td></tr>");
        out.println("<tr><td class=crbr>17.</td><td style=font-size:32px>ظ</td><td>Za</td><td>900 | 800</td><td class=crbr>18.</td><td style=font-size:32px>ع</td><td>Ajn</td><td>70</td><td class=crbr>19.</td><td style=font-size:32px> غ </td><td>Gajn</td><td>1000 | 900</td><td class=crbr>20.</td><td style=font-size:32px> ف </td><td>Fa</td><td>80</td></tr>");
        out.println("<tr><td class=crbr>21.</td><td style=font-size:32px>ق</td><td>Kaf</td><td>100</td><td class=crbr>22.</td><td style=font-size:32px>ك</td><td>Kef</td><td>20</td><td class=crbr>23.</td><td style=font-size:32px>ل</td><td>Lam </td><td>30</td><td class=crbr>24.</td><td style=font-size:32px> م </td><td>Mim</td><td>40</td></tr>");
        out.println("<tr><td class=crbr>25.</td><td style=font-size:32px>ن</td><td>Nun</td><td>50</td><td class=crbr>26.</td><td style=font-size:32px>ه</td><td>Ha</td><td>5</td><td class=crbr>27.</td><td style=font-size:32px>و</td><td>Vav</td><td>6</td><td class=crbr>28.</td><td style=font-size:32px> ى </td><td>Ja</td><td>10</td></tr></table>");
		
        String redni_brojevi = request.getParameter("redni_brojevi");
        String dan_noc = request.getParameter("dan_noc");
		String max_imena = request.getParameter("max_imena");
        if (redni_brojevi != null || dan_noc != null || max_imena != null) {
            out.println("<p style=color:green>" + rb.getString("firsttry.rredni_brojevi"));
            out.println(filter(redni_brojevi) + ",");
            out.println(rb.getString("firsttry.rdan_noc"));
            out.println(Dnnc(filter(dan_noc)) + ": " + filter(dan_noc) );
			out.println(rb.getString("firsttry.rmax_imena"));
            out.println(": " + filter(max_imena) + "<br>");
			out.println(wrapperImena(redni_brojevi, dan_noc, max_imena) + "</p>");
			
        } else {
            out.println("<br>");out.println("<p style=color:red>" + rb.getString("firsttry.no-params")+ "</p>");
        }
        out.println("<P>");
        out.print("<form action=\"");
        out.print("firsttry\" ");
        out.println("method=POST>");
        out.println("<table><tr><td>" + rb.getString("firsttry.redni_brojevi") + "</td>");
        out.println("<td><input type=text size=20 name=redni_brojevi></td></tr>");
        out.println("<tr><td>" + rb.getString("firsttry.dan_noc") + "</td>");
        out.println("<td><input type=text size=20 name=dan_noc></td>");
       	out.println("<tr><td>" + rb.getString("firsttry.max_imena")+ "</td>");
        out.println("<td><input type=text size=20 name=max_imena></td></tr><tr><td class=cright>Free usage, Copyright & Content Protection © 2024 by Omer Jerlagić </td>");
        //out.println("<br>");
		result2 = new ArrayList<>();
        currentCombination2 = new ArrayList<>();
        out.println("<td><input type=submit value='Računaj kombinacije'></td></tr></table>");
        out.println("</form>");
		out.println("</P>");
		out.println("<p><b>ZA VIŠE INFORMACIJA:</b> Preporučujemo čitanje djela TRAŽENJE POMOĆI I PODRŠKE S ALLAHOVIM LIJEPIM IMENIMA, Šejh Jusuf ibn Ismail En-Nebehani k.s.,<br>");
		out.println("Izdavač: Udruženje za edukaciju mladih i afirmaciju pozitivnih vrijednosti ABDULKADIR GEJLANI, Sarajevo, 2024.</p>");
		out.println("<p><b>ZA SVE PRIMJEDBE I SUGESTIJE: ✉ </b> <em>yomer17@yahoo.com </em></p>");
        out.println("</body>");
        out.println("</html>");}
		 finally {
        out.close();  // Always close the output writer
      }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        doGet(request, response);
    }
	
    private static String Dnnc (String huruf) {
        if (huruf.equals("1")) {return " DNEVNI ";} else {return " NOĆNI ";}}
	
    private static String wrapperImena (String prbrs, String pdn12, String pdomax) {
        String tinitivra = "";
		if (prbrs.trim().isEmpty()) {prbrs = "18 24 10";} 
		if (!AreOK(prbrs)) {tinitivra = tinitivra + ("Unešeni brojevi nemaju vrijednosti rednih brojeva od 1 do 28! Pokušajte ponovo..");}
		if (pdn12.trim().isEmpty()) {pdn12 = "1";}
		int target = Zbir(prbrs, pdn12); 
		if (pdomax.trim().isEmpty()) {pdomax = "2";}
        int maxImena = Integer.parseInt(pdomax);
        List<List<Integer>> result = findCombinations(target, maxImena);
        if (result.isEmpty()) {tinitivra = tinitivra + ("Nema kombinacija koje daju zadati zbir.");} else {
	    tinitivra = tinitivra + ("<strong>KOMBINACIJE ZA ZBIR HARFOVA ~ "+target+" ~ ["+ Vrj(prbrs, pdn12) +"] ZA"+Dnnc(pdn12)+"ZIKR SU:</strong><br>");
	    int i=0;
        result.sort((x, y) -> {return x.size() - y.size();});
        result2.sort((x, y) -> {return x.size() - y.size();});
            for (List<Integer> combination : result) {
                if (combination.size()<=maxImena){
	        	i++; 
                tinitivra = tinitivra + ("Rbr: "+i+". Kombinacija brojeva: "+combination +"      "+ NamesStr2(result2.get(i-1))) + "<br>";
                }
            }
        tinitivra = tinitivra + ("<strong>Ukupno kombinacija: "+i+" za zadati broj: "+target+ " i zadati maksimalni broj imena: "+maxImena+".</strong>");
        }
		return tinitivra;
	}
	
    private static List<List<Integer>> findCombinations(int target, int maxImena) {
        List<List<Integer>> result = new ArrayList<>();
        findCombinations(target, 0, new ArrayList<>(), result, maxImena);
        return result;
    }
	
	private static void findCombinations(int target, int start, List<Integer> currentCombination,  List<List<Integer>> result, int maxImena) {
        if (target == 0) {
            result.add(new ArrayList<>(currentCombination));
            result2.add(new ArrayList<>(currentCombination2));
            return;
        }

        for (int i = start; i < NUMBERS.length; i++) {
            if (NUMBERS[i] <= target && currentCombination.size() < maxImena + 1) {
                try {currentCombination.add(NUMBERS[i]);
                currentCombination2.add(i);
                findCombinations(target - NUMBERS[i], i + 1, currentCombination, result, maxImena);
                currentCombination.remove(currentCombination.size() - 1);
                currentCombination2.remove(currentCombination2.size() - 1);
                }
                catch (OutOfMemoryError oome) {
                    System.err.println("Prevelik zbir brojeva "+ target +" u imenu da bi se računalo sve kombinacije za zadati maksimalni broj imena: "+maxImena+".");
                    System.err.println(i+" Ukupno kombinacija 2: "+currentCombination2);
                    System.err.println("Max JVM memory: " + Runtime.getRuntime().maxMemory());
                }
              }
         }
    }
	
	private static int Zbir (String huruf, String dannoc) {
        int zbir = 0;
        String[] numbers = huruf.trim().split("\\s+");
	    if (dannoc.equals("1")) {
            for (String number : numbers) {
                zbir  += HURUF_DAY[Integer.parseInt(number)-1];
            }}
        else {for (String number : numbers) {
            zbir  += HURUF_NIGHT[Integer.parseInt(number)-1];
        }}
        return zbir;
    }
	
	private static boolean AreOK (String huruf) {
        boolean isOk = true;
        String[] numbers = huruf.trim().split("\\s+");
        for (String number : numbers) {
            if (Integer.parseInt(number) > 0 && Integer.parseInt(number) < 29 ) {isOk = true & isOk; }
            else {isOk = false & isOk; };
        }
        return isOk;
    }
	
	private static String Vrj (String huruf, String dannoc) {
        String vrj = "";
        String[] numbers = huruf.trim().split("\\s+");
        if (dannoc.equals("1")) {
            for (String number : numbers) {
                vrj  += "+" + HURUF_DAY[Integer.parseInt(number)-1];
            }}
        else {for (String number : numbers) {
            vrj  += "+" + HURUF_NIGHT[Integer.parseInt(number)-1];
        }}
        return vrj;
    }
	
	private static String NamesStr2 (List<Integer> reso) {
        String sastav = "Sastav imena:";
        for( Integer parentItem : reso) {
            sastav = sastav +" # "+ NAMES[(Integer)(parentItem)];
        }
        return sastav;
    }
	
	public static String filter(String message) {
        if (message == null) {return null;}
        char content[] = new char[message.length()];
        message.getChars(0, message.length(), content, 0);
        StringBuilder result = new StringBuilder(content.length + 50);
        for (char c : content) {
            switch (c) {
                case '<':
                    result.append("&lt;");
                    break;
                case '>':
                    result.append("&gt;");
                    break;
                case '&':
                    result.append("&amp;");
                    break;
                case '"':
                    result.append("&quot;");
                    break;
                default:
                    result.append(c);
            }
        }
        return result.toString();
    }
}

