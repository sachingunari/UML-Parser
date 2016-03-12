import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.BodyDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import net.sourceforge.plantuml.SourceStringReader;

public class Parser {

	static String Finalstring = "@startuml \n skinparam classAttributeIconSize 0 \n";
	static String ascStr ="";
	static String relStr ="";
	static String depStr ="";
	static String depcStr ="";
	static String clsStr ="";
	static ArrayList <String> RList = new ArrayList<String>();
	static ArrayList <String> CIList = new ArrayList<String>();
	static ArrayList <String> IList = new ArrayList<String>();
	static ArrayList <String> CList = new ArrayList<String>();
	static ArrayList <String> CIGList = new ArrayList<String>();
	static ArrayList <String> CISList = new ArrayList<String>();
	static ArrayList <String> CICList = new ArrayList<String>();
	static ArrayList <String> CIBList = new ArrayList<String>();
	static ArrayList <String> CVList = new ArrayList<String>();
	static ArrayList <String> CVGList = new ArrayList<String>();
	static ArrayList <String> CVSList = new ArrayList<String>();
	static ArrayList <String> CVGUList = new ArrayList<String>();
	static ArrayList <String> CVSUList = new ArrayList<String>();
	static ArrayList <String> CVGCList = new ArrayList<String>();
	static ArrayList <String> CVSCList = new ArrayList<String>();
	static ArrayList <String> CGSList = new ArrayList<String>();
	//static ArrayList <String> CMPList = new ArrayList<String>();
	 public static void main(String[] args) throws Exception {
		 
		 
		 File path = new File("C:/Users/Sachin/Desktop/tests2");
		 
		 int j=1;
		 
		 
		 if(j>0){
		 
		 File [] filess = path.listFiles();
		    for (int i = 0; i < filess.length; i++){
		        if (filess[i].isFile()){ //this line weeds out other directories/folders
		   //        System.out.println(files[i]);
		     //   }
		    // }
	        // creates an input stream for the file to be parsed
	        FileInputStream inn = new FileInputStream(filess[i]);

	        CompilationUnit cuu;
	        try {
	            // parse the file
	            cuu = JavaParser.parse(inn);
	        } finally {
	            inn.close();
	        }
	        
	        getClasses(cuu);
	        getVariables(cuu);
	        getMethodParameters(cuu);
		        }}
		    
		    j--;
		 }
		 	 
		 
		 
		    File [] files = path.listFiles();
		    for (int i = 0; i < files.length; i++){
		        if (files[i].isFile()){ //this line weeds out other directories/folders
		   //        System.out.println(files[i]);
		     //   }
		    // }
		        	
		     
	        // creates an input stream for the file to be parsed
	        FileInputStream in = new FileInputStream(files[i]);

	        CompilationUnit cu;
	        try {
	            // parse the file
	            cu = JavaParser.parse(in);
	        } finally {
	            in.close();
	        }
	        
	       // change the methods names and parameters
	       
	       new ClassVisitor().visit(cu, null);
	       new VariableVisitor().visit(cu, null);
	       new ConstructorVisitor().visit(cu, null);
	   	   new MethodVisitor().visit(cu, null);
	   	

	 
	   	Finalstring += "}\n";
	   		   	
	  	   	
	   	 }
		        }  
		   
		    Finalstring += relStr;
		   // Finalstring += relStr1;
		    Finalstring += depStr;
		    Finalstring += depcStr;
	        Finalstring += "@enduml";
	        
	        
	       
	        
	       System.out.println(Finalstring);
	      
	        //System.out.println(relStr);
	        //System.out.println(CGSList); 
	        
	        
	    	SourceStringReader reader = new SourceStringReader(Finalstring);
	    	// Write the first image to "png"
	         reader.generateImage(new File("C:\\Users\\Sachin\\Desktop\\TESTPattern.png"));
	                 
	    }
	  
	 
	 private static void getClasses(CompilationUnit cu) {
		 List<TypeDeclaration> types = cu.getTypes();
	        for (TypeDeclaration type : types) {
	           // List<BodyDeclaration> members = type.getMembers();
	          //  for (TypeDeclaration member : members) {
	                if (type instanceof ClassOrInterfaceDeclaration) {
	                	ClassOrInterfaceDeclaration m = (ClassOrInterfaceDeclaration) type;
	                if(m.isInterface())
	                {
	                	IList.add(m.getName());
	                }
	                else{
	                	CList.add(m.getName());
	                }
                	 CIList.add((m.getName()));
                	 CICList.add("Collection<"+m.getName()+">");
                	 CIBList.add(m.getName()+"[]");
                	 CISList.add("set"+m.getName());
                	 CIGList.add("get"+m.getName());
                	 
                }
           //  }
         }
     }
	 
	 private static void getMethodParameters(CompilationUnit cu) {
		 List<TypeDeclaration> types = cu.getTypes();
	       for (TypeDeclaration type : types) {
	            List<BodyDeclaration> members = type.getMembers();
	           for (BodyDeclaration member : members) {
	        	   if (member instanceof MethodDeclaration) {
	        		   MethodDeclaration m = (MethodDeclaration) member;
	                	String s="";
		        		 int PList= m.getParameters().size();
		 
			      		for(int i=0;i<PList;i++){
		        	s = s + m.getParameters().get(i).getId().getName()+ " :" + m.getParameters().get(i).getType() + " " ;

		        	  		}
			      		
			        		 CGSList.add(m.getName());
			        		
                	               	
              }
            }
         }
     }
    

	 private static void getVariables(CompilationUnit cu) {
		 List<TypeDeclaration> types = cu.getTypes();
	        for (TypeDeclaration type : types) {
	            List<BodyDeclaration> members = type.getMembers();
	           for (BodyDeclaration member : members) {
	                if (member instanceof FieldDeclaration) {
	                	FieldDeclaration m = (FieldDeclaration) member;
                	 CVList.add((m.getVariables().get(0).getId().getName()));
                	 CVGList.add("get"+(m.getVariables().get(0).getId().getName()));
                	 CVSList.add("set"+(m.getVariables().get(0).getId().getName()));
                	 CVGUList.add("get"+(m.getVariables().get(0).getId().getName().substring(0,1).toUpperCase())+(m.getVariables().get(0).getId().getName().substring(1)));
                	 CVSUList.add("set"+(m.getVariables().get(0).getId().getName().substring(0,1).toUpperCase())+(m.getVariables().get(0).getId().getName().substring(1)));
                	 CVGCList.add("get"+(Character.toUpperCase(m.getVariables().get(0).getId().getName().charAt(0)) + m.getVariables().get(0).getId().getName().substring(1)));  
                	 CVSCList.add("set"+(Character.toUpperCase(m.getVariables().get(0).getId().getName().charAt(0)) + m.getVariables().get(0).getId().getName().substring(1)));
                	
                 }
             }
         }
     }
	 
	 
	 
	 
	 private static class ConstructorVisitor extends VoidVisitorAdapter {

		 

	        @Override
		        public void visit(ConstructorDeclaration n, Object arg) {		        
		     
		            // here you can access the attributes of the method.
		        	// this method will be called for all methods in this 
		            // CompilationUnit, including inner class methods
		        	
	        	String s="";
       		 int PList= n.getParameters().size();
	      		for(int i=0;i<PList;i++){
	      			if(IList.contains(n.getParameters().get(i).getType().toString()))
	      					{
	      				String rel1= clsStr+"..>" + n.getParameters().get(i).getType().toString()+ ":uses";
	 					String rel2= n.getParameters().get(i).getType().toString()+"..>" +clsStr + ":uses";
	 					
	 					if(!depStr.contains(rel1) && !depStr.contains(rel2)){
	      				depcStr +=clsStr+"..>" + n.getParameters().get(i).getType().toString() + ":uses \n";
	      				
	 					}
	      			}
       	s = s + n.getParameters().get(i).getId().getName()+ " :" + n.getParameters().get(i).getType() + " " ;
     
	      		}
	     		Finalstring += "+";
	     		  Finalstring += (n.getName() + "("+s+") \n ");	
	     	  
	 
	        }	
	 }	
	 
	 
	 
	 private static class MethodVisitor extends VoidVisitorAdapter {

		 

	        @Override
	        public void visit(MethodDeclaration n, Object arg) {
	        	
	        
	        	 if( !(CVGList.contains(n.getName())) && !(CVSList.contains(n.getName())) && !(CISList.contains(n.getName())) && !(CIGList.contains(n.getName())) && !(CVGUList.contains(n.getName())) && !(CVSUList.contains(n.getName()))) 

	        	{
	            // here you can access the attributes of the method.
	        	// this method will be called for all methods in this 
	            // CompilationUnit, including inner class methods
	        		 
	        		 
	        		 if(n.getBody() != null){
	 	    	        List<Statement> statementList =	n.getBody().getStmts();
	 	    	        if(statementList!=null){
	 	        for (Statement statement1 : statementList) {
	 	        	
	 	        	for(String interface1 : IList){	
	 					if(statement1.toStringWithoutComments().contains(interface1)){
	 					String rel1= clsStr+"..>" + interface1.toString()+ ":uses";
	 					String rel2= interface1.toString()+"..>" +clsStr + ":uses";
	 						
	 					if(!depStr.equals(rel1) && !depStr.equals(rel2)){
	 						depStr =clsStr+"..>" + interface1.toString()+ ":uses \n";
	 					
	 					}
	 				 }
	 				}
	 			}
	        }
      }
	        		 
	        		
	        		String s="";
	        		
	        		 int PList= n.getParameters().size();
		      		for(int i=0;i<PList;i++){
		      			if(IList.contains(n.getParameters().get(i).getType().toString()) && (!IList.contains(ascStr)))
		      			{
		      				if(!depStr.contains(ascStr+"..>" + n.getParameters().get(i).getType().toString()+ ":uses")){
		      				depStr +=ascStr+"..>" + n.getParameters().get(i).getType().toString()+ ":uses \n";
		      				//System.out.println(depStr);
		      				}
		      				}
	        	s = s + n.getParameters().get(i).getId().getName()+ " :" + n.getParameters().get(i).getType() + " " ;
		      		}
		      		
		      	     		
		      		
		      		
	        	if (n.getModifiers()==1)
        		{
        		Finalstring += "+";
        		  Finalstring += (n.getName() + "("+s +") : ");      		  
        		 
  	            Finalstring += ((n.getType()) + "\n");
        		}
        	
        	else if (n.getModifiers()==1025){
        		Finalstring += "+";
        		Finalstring += (n.getName() + "("+s +") : ");
 	            Finalstring += ((n.getType()) + "\n");
        	} 
	        	
        	else if (n.getModifiers() %2==1){
        		Finalstring += "{static}";
        		Finalstring += "+";
        		Finalstring += (n.getName() + "("+s +") : ");
 	            Finalstring += ((n.getType()) + "\n");
        	} 
        	        	
        	
     	}
	        	         	 
    
    }
 }	
	 
	 private static class ClassVisitor extends VoidVisitorAdapter {
		 
		 	        @Override
	        public void visit(ClassOrInterfaceDeclaration n, Object arg) {
	            // here you can access the attributes of the method.
	        	 // this method will be called for all methods in this 
	            // CompilationUnit, including inner class methods
	        	
	        
		 	        	
		 	  //  int s=CList.size();
		 	    
	        	if(n.getExtends() != null){
	        		Finalstring += (n.getExtends().get(0).getName() + "<|-- " + n.getName()+ "\n");
	        	  
	        	}
	        	if(n.getImplements() != null){
	        		
	        		List <ClassOrInterfaceType> IList= n.getImplements();
	        		int n1= IList.size();
	        		for(int i=0;i<n1;i++){
	        		
	        		Finalstring += (n.getImplements().get(i).getName() + "<|.. " + n.getName()+ "\n");
	        	
	        		}
	        	}
	        	
	        
	           
	        	if (n.isInterface()){
	        		//CIList.add(n.getName());
	        		Finalstring += (" interface " );  
		        	
		        	Finalstring += (n.getName()+" <<interface>>" + " { \n ");
		        	ascStr=null;
		        	ascStr=n.getName();
	        		
	        	}
	        	else {
	        		
	        //	CIList.add(n.getName());
	        		        		
	        	Finalstring += (" class " );  
	        	
	        	Finalstring += (n.getName() + " { \n ");
	        	clsStr=null;
	        	ascStr=null;
	        	ascStr=n.getName();
	        	clsStr=n.getName();
	          
	        	}
	        }
	    }
	 
	 private static class VariableVisitor extends VoidVisitorAdapter {

	        @Override
	        public void visit(FieldDeclaration n, Object arg) {
	            // here you can access the attributes of the method.
	            // this method will be called for all methods in this 
	            // CompilationUnit, including inner class methods
	        	 
	        	// If condition to Ignore package and Protected Scope
	        	//if (CIList.contains((n.getType()))==false){
	        	//String Collection;
	        	String varc="get"+Character.toUpperCase(n.getVariables().get(0).getId().getName().charAt(0))+n.getVariables().get(0).getId().getName().substring(1);
			    if ((CICList.contains(n.getType().toString())==false) && (CIList.contains(n.getType().toString())==false) && (CIBList.contains(n.getType().toString())==false)){
	        		if (n.getModifiers()==1)
	        		{
	        		Finalstring += "+";	        				        	 
		        	Finalstring += ((n.getVariables().get(0).getId().getName())+" : ");
		        	Finalstring += ((n.getType()) + "\n");
		        	//System.out.println(n.getType.()+ "hiiiiiiii");
	        		}
	        	else if ((n.getModifiers()==2) && (CGSList.contains(varc) )){
	        		Finalstring += "+";
	        		Finalstring += ((n.getVariables().get(0).getId().getName())+" : ");
		        	Finalstring += ((n.getType()) + "\n");
		        	//System.out.println(varc);
	        	}
	        	else if ((n.getModifiers()==2)){
	        	
	        		Finalstring += "-";
	        		Finalstring += ((n.getVariables().get(0).getId().getName())+" : ");
		        	Finalstring += ((n.getType()) + "\n");
	        	  	//System.out.println(n.getType()+"-->attached");
		        	//System.out.println("get"+Character.toUpperCase(n.getVariables().get(0).getId().getName().charAt(0))+n.getVariables().get(0).getId().getName().substring(1)+"hiiiiiiiiiiiiii");
	        	}
	        //	Finalstring += ((n.getType()) + " ");
	        	
	        //	Finalstring += ((n.getVariables().get(0).getId().getName())+"\n");	
	        		
	        			        
		 }
								    
	    else if ((CICList.contains(n.getType().toString())==true)&& (n.getModifiers()==1 || n.getModifiers()==2)){ //|| (CIList.contains(n.getType().toString())==true)){
						        		
	    		//ascStr = "--"+StringUtils.substringsBetween(lineOfText , "\"", "\"");
			String name = n.getType().toString().substring(n.getType().toString().indexOf("<")+1, n.getType().toString().lastIndexOf(">"));
			String rel="";	
			String rel1="";
			String rel2="";
			String rel3="";
			String rel4="";
			//System.out.println("Interface name : " +name);
			rel += (ascStr+" \"1\" -- \"*\" "+name+"\n");
			rel1=(name+" \"1\" -- \"1\" "+ascStr+"\n");
			rel2=(name+" \"1\" -- \"*\" "+ascStr+"\n");
			rel3=(name+" \"*\" -- \"1\" "+ascStr+"\n");
			rel4=(name+" \"*\" -- \"*\" "+ascStr+"\n");
			if(!relStr.contains(rel1) && !relStr.contains(rel2) && !relStr.contains(rel3) && !relStr.contains(rel4)){
				relStr += (rel+"\n");
				}
			
			//Class01 "1" *-- "many" Class02
			RList.add(ascStr);
        		    	
		    	}
								    
	    else if ((CIBList.contains(n.getType().toString())==true) && (n.getModifiers()==1 || n.getModifiers()==2) ){ 
    		
    		//ascStr = "--"+StringUtils.substringsBetween(lineOfText , "\"", "\"");
		String name = n.getType().toString().substring(+0, n.getType().toString().lastIndexOf("["));
		String rel="";
		String rel1="";
		String rel2="";
		String rel3="";
		String rel4="";
		//System.out.println("Interface name : " +name);
		rel += (ascStr+" \"1\" -- \"*\" "+name+"\n");
		rel1=(name+" \"1\" -- \"1\" "+ascStr+"\n");
		rel2=(name+" \"1\" -- \"*\" "+ascStr+"\n");
		rel3=(name+" \"*\" -- \"1\" "+ascStr+"\n");
		rel4=(name+" \"*\" -- \"*\" "+ascStr+"\n");
		if(!relStr.contains(rel1) && !relStr.contains(rel2) && !relStr.contains(rel3) && !relStr.contains(rel4)){
			relStr += (rel+"\n");
			}
		//System.out.println(relStr+"hiiiiiiiiiiiii");
		//Class01 "1" *-- "many" Class02
		RList.add(rel);
		
    	    	
    	
	    	}	
	    else if ((CIList.contains(n.getType().toString())==true) && (n.getModifiers()==1 || n.getModifiers()==2) ){ 
    		
    		//ascStr = "--"+StringUtils.substringsBetween(lineOfText , "\"", "\"");
		String name = n.getType().toString();
		String rel="";
		String rel1="";
		String rel2="";
		String rel3="";
		String rel4="";
		//System.out.println("Interface name : " +ascStr);
		rel += (ascStr+" \"1\" -- \"1\" "+name+"\n");
		rel1=(name+" \"1\" -- \"1\" "+ascStr+"\n");
		rel2=(name+" \"1\" -- \"*\" "+ascStr+"\n");
		rel3=(name+" \"*\" -- \"1\" "+ascStr+"\n");
		rel4=(name+" \"*\" -- \"*\" "+ascStr+"\n");
		
		//System.out.println("Interface name : " +rel1);
		if(!relStr.contains(rel1) && !relStr.contains(rel2) && !relStr.contains(rel3) && !relStr.contains(rel4)){
		relStr += (rel+"\n");
		}
		
		RList.add(rel);
		
    	
	    	}
								    
	       // }
								   
	 }
	        
	 
	}}