package main;

import lib.New_grammarParser;
import lib.New_grammarVisitor;
import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;

import java.util.StringTokenizer;
public class Visitor <Object> extends AbstractParseTreeVisitor<Object> implements New_grammarVisitor<Object> {
    String[] system_names =new String[100];
    int index_names=0;
    String[] created_systems =new String[100];
    int index_created=0;
    String[][] system_declaration=new String[200][2];
    String[] system_subcomponents=new String [100];
    int index_subcomponents=0;
    String[] connections=new String[100];
    int index_connections=0;
    Integer number_of_connections[]=new Integer [100];
    String[] sys_features=new String[100];
    String [] system_in_features= new String[100];
    int ind_system_in_features=0;
    int index_features=0;
    /**
     * Visit a parse tree produced by {@link New_grammarParser#nlparch}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    @Override
    public Object visitNlparch(New_grammarParser.NlparchContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * Visit a parse tree produced by {@link New_grammarParser#sentences}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    @Override
    public Object visitSentences(New_grammarParser.SentencesContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * Visit a parse tree produced by {@link New_grammarParser#sentence}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    @Override
    public Object visitSentence(New_grammarParser.SentenceContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * Visit a parse tree produced by {@link New_grammarParser#structural_stmts}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    @Override
    public Object visitStructural_stmts(New_grammarParser.Structural_stmtsContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * Visit a parse tree produced by {@link New_grammarParser#structural_stmt}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    @Override
    public Object visitStructural_stmt(New_grammarParser.Structural_stmtContext ctx) {

        String struct_stmt=ctx.getText();
        String struct_names="";
        boolean check_same_struct_name=false;
        String[] system_consists=new String[10];
        int index_consists=0;
        int catch_sub_comp=0;
        int index_of_already_sub_comp=-1;
        for(int i=0;i<struct_stmt.length();i++)
        {
            if((struct_stmt.charAt(i)>=65 && struct_stmt.charAt(i)<=90) || (struct_stmt.charAt(i)==95))
            {
                struct_names=struct_names+struct_stmt.charAt(i);
            }
            else
            {
                if(catch_sub_comp==0)
                {   boolean check_already_sub_comp=false;
                    for (int j=0;j<=index_subcomponents-1;j++)
                    {   StringTokenizer sub_comp_separate = new StringTokenizer(system_subcomponents[j]," ");
                        String name_of_eachcomp="";
                        name_of_eachcomp=sub_comp_separate.nextToken();
                        if(name_of_eachcomp.equals(struct_names))
                        {
                            check_already_sub_comp=true;
                            index_of_already_sub_comp=j;
                        }
                    }
                    if(!check_already_sub_comp)
                    {
                        system_subcomponents[index_subcomponents]=struct_names;
                        index_subcomponents+=1;
                    }
                    catch_sub_comp+=1;
                }
                else
                {
                    if(index_of_already_sub_comp==-1)
                    {
                        system_subcomponents[index_subcomponents-1]=system_subcomponents[index_subcomponents-1]+" "+struct_names;
                    }
                    else
                    {
                        system_subcomponents[index_of_already_sub_comp]=system_subcomponents[index_of_already_sub_comp]+" "+struct_names;
                    }
                }
                if(index_names==0)
                {
                    system_names[index_names]=struct_names;
                    index_names+=1;
                    struct_names="";
                }
                else
                {   check_same_struct_name=false;
                    for (int j=0;j<=index_names-1;j++)
                    {
                        if(system_names[j].equals(struct_names))
                        {
                            check_same_struct_name=true;
                        }
                    }
                    if(!check_same_struct_name && (!struct_names.equals("")))
                    {
                        system_names[index_names]=struct_names;
                        index_names+=1;
                        struct_names="";
                    }
                    else
                    {
                        struct_names="";
                    }
                }
            }
            if(i==struct_stmt.length()-1)
            {   if(index_of_already_sub_comp==-1)
            {
                system_subcomponents[index_subcomponents-1]=system_subcomponents[index_subcomponents-1]+" "+struct_names;
            }
            else
            {
                system_subcomponents[index_of_already_sub_comp]=system_subcomponents[index_of_already_sub_comp]+" "+struct_names;
            }
                check_same_struct_name=false;
                for (int j=0;j<=index_names-1;j++)
                {
                    if(system_names[j].equals(struct_names))
                    {
                        check_same_struct_name=true;
                    }
                }
                if(!check_same_struct_name)
                {
                    system_names[index_names]=struct_names;
                    index_names+=1;
                    struct_names="";
                }
            }
        }
        struct_names="";
        for(int i=0;i<struct_stmt.length();i++) {
            if ((struct_stmt.charAt(i) >= 65 && struct_stmt.charAt(i) <= 90) || (struct_stmt.charAt(i) == 95)) {
                struct_names = struct_names + struct_stmt.charAt(i);
            }
            else
            {   if(!(struct_names.equals("")))
            {
                system_consists[index_consists] = struct_names;
                index_consists = index_consists + 1;
                struct_names="";
            }
            }
            if(i==struct_stmt.length()-1)
            {
                system_consists[index_consists] = struct_names;
                index_consists = index_consists + 1;
                struct_names="";
            }
        }
        boolean check_sys_name=false;
        for(int i=0;i<=index_names-1;i++) {
            check_sys_name=false;
            String x[] = new String[2];
            for (int j = 0; j <= index_created-1; j++) {
                if (system_names[i].equals(created_systems[j])) {
                    check_sys_name = true;
                }
            }
            if (check_sys_name) {
                continue;
            } else {
                x = system_generate(system_names[i]);
                created_systems[index_created] = system_names[i];
                index_created=index_created+1;
                for (int k = 0; k <= x.length - 1; k++) {
                    system_declaration[i][k] = x[k];
                }

            }
        }
        String[] args=new String[index_consists-1];
        int index_args=0;
        for(int i=0;i<=index_names-1;i++)
        {
            if(system_names[i].equals(system_consists[0]))
            {
                for(int j=1;j<=index_consists-1;j++)
                {
                    args[index_args]=system_consists[j];
                    index_args+=1;
                }
            }
        }
        int newline_count=0;
        String new_string="";
        String sys_sub_comp=subcomponents_generation(args);
        for(int i=0;i<=index_names-1;i++)
        {
            if(system_names[i].equals(system_consists[0]))
            {
                for(int j=0;j<=system_declaration[i][1].length()-1;j++)
                {
                    if(system_declaration[i][1].charAt(j)=='\n' && newline_count==0)
                    {
                        new_string=new_string+sys_sub_comp;
                        new_string=new_string+"\t\tthis_"+system_names[i]+": system "+system_names[i]+";\n";
                        system_in_features[ind_system_in_features]=system_names[i];
                        ind_system_in_features+=1;
                        newline_count+=1;
                    }
                    else
                    {
                        new_string=new_string+system_declaration[i][1].charAt(j);
                    }
                }
                system_declaration[i][1]=new_string;
            }
        }

        return null;
    }

    public String subcomponents_generation(String[] args)
    {
        String ret_subcomponent="\n\tsubcomponents\n ";
        for(int i=0;i<=args.length-1;i++)
        {
            ret_subcomponent = ret_subcomponent +"\t\t"+"this_"+args[i]+": system "+args[i]+".impl;"+"\n";
        }
        return ret_subcomponent;
    }

    public String[] system_generate(String sys_name) {
        String system_start="system "+sys_name;
        String system_end="end "+sys_name+";"+"\n";
        String system_imp_start= "system implementation "+sys_name+".impl"+"\n";
        String system_imp_end="end "+sys_name+".impl;"+"\n";
        String[] sys_return =new String[2];
        sys_return[0]=system_start+"\n"+system_end;
        sys_return[1]=system_imp_start+system_imp_end;
        return sys_return;
    }

    /**
     * Visit a parse tree produced by {@link New_grammarParser#connection_stmt}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    @Override
    public Object visitConnection_stmt(New_grammarParser.Connection_stmtContext ctx) {
        String path_to_first=ctx.Struct_noun().toString();
        String path_to_second="";
        boolean under_same_component=false;
        for(int i=0;i<=index_subcomponents-1;i++)
        {
            if(system_subcomponents[i].contains(ctx.Struct_noun().toString()) && system_subcomponents[i].contains(ctx.struct_multinoun().Struct_noun(0).getText()))
            {
                under_same_component=true;
                path_to_second=ctx.struct_multinoun().Struct_noun(0).getText();
//                                   //System.out.println("Under same component");
            }

        }
        if(!under_same_component) {
            int index_of_first_comp_upper = -1;
            boolean found_it = false;
            int index_of_second_comp_upper = -1;
            for (int i = 0; i <= index_subcomponents - 1; i++) {
                if (system_subcomponents[i].contains(ctx.struct_multinoun().Struct_noun(0).getText())) {
                    index_of_second_comp_upper = i;
                }
                if (system_subcomponents[i].contains(ctx.Struct_noun().toString())) {
                    index_of_first_comp_upper = i;
                }
            }

            int copy_of_second_comp = index_of_second_comp_upper;
            String second = ctx.struct_multinoun().Struct_noun(0).getText();
            while (index_of_first_comp_upper >= 0) {
                StringTokenizer st = new StringTokenizer(system_subcomponents[index_of_first_comp_upper], " ");
                String first = st.nextToken();
                if(first.equals(path_to_first))
                {

                }
                else {
                    path_to_first = first + "-" + path_to_first;
                }
                second = ctx.struct_multinoun().Struct_noun(0).getText();
                path_to_second = second;
                for (int i = 0; i <= index_subcomponents - 1; i++) {
                    if (system_subcomponents[i].contains(ctx.struct_multinoun().Struct_noun(0).getText())) {
                        index_of_second_comp_upper = i;
                    }
                }
                while (index_of_second_comp_upper >= 0) {
                    for (int i = 0; i <= index_subcomponents - 1; i++) {
                        if (system_subcomponents[i].contains(first) && system_subcomponents[i].contains(second)) {
                            StringTokenizer st3 = new StringTokenizer(system_subcomponents[i], " ");
                            String common_comp_for_conn = st3.nextToken();
                            if(path_to_first.contains(common_comp_for_conn))
                            {

                            }
                            else {
                                path_to_first = common_comp_for_conn + "-" + path_to_first;
                            }
                            if(path_to_second.contains(common_comp_for_conn))
                            {

                            }
                            else {
                                path_to_second = common_comp_for_conn + "-" + path_to_second;
                            }
                            found_it = true;
                            break;
                        }
                    }
                    if (found_it) {
                        break;
                    } else {
                        if (index_of_second_comp_upper == 0) {
                            break;
                        }
                        for (int j = 0; j <= index_of_second_comp_upper; j++) {
                            boolean val=false;
                            if (system_subcomponents[j].contains(second)) {
                                index_of_second_comp_upper = j;
                                StringTokenizer st2 = new StringTokenizer(system_subcomponents[index_of_second_comp_upper], " ");
                                String new_second = st2.nextToken();
                                if(new_second.equals(second))
                                {
                                    for (int k = 0; k <= index_of_second_comp_upper-1; k++) {
                                        if (system_subcomponents[k].contains(second)) {
                                            index_of_second_comp_upper = k;
                                            st2 = new StringTokenizer(system_subcomponents[index_of_second_comp_upper], " ");
                                            second = st2.nextToken();
                                            path_to_second = second + "-" + path_to_second;
                                            val=true;
                                            break;
                                        }
                                    }
                                }
                                else {
                                    second=new_second;
                                    path_to_second = second + "-" + path_to_second;
                                    val=true;
                                    break;
                                }
                                if(val)
                                {
                                    break;
                                }
                            }
                        }
                    }
                }
                if (found_it) {
                    break;
                } else {
                    if (index_of_first_comp_upper == 0) {
                        break;
                    }
                    for (int j = 0; j <= index_of_first_comp_upper; j++) {
                        boolean val=false;
                        if (system_subcomponents[j].contains(first)) {
                            index_of_first_comp_upper = j;
                            StringTokenizer st2 = new StringTokenizer(system_subcomponents[index_of_first_comp_upper], " ");
                            String new_first = st2.nextToken();
                            if(first.equals(new_first)) {
                                for (int k = 0; k <= index_of_first_comp_upper-1; k++) {
                                    //System.out.println("Inner first");
                                    if (system_subcomponents[k].contains(first)) {
                                        index_of_first_comp_upper = k;
                                        val=true;
                                        break;
                                    }
                                }
                            }
                            else{
                                val=true;
                                break;
                            }
                            if(val)
                            {
                                break;
                            }
                        }
                    }
                }
            }

        }

        // For the output port of start
        boolean already_created_feature=false;
        int if_present_first_index=-1;
        for(int i=0;i<=index_features-1;i++)
        {
            StringTokenizer st = new StringTokenizer(sys_features[i]," ");
            String comp_name=st.nextToken();
            String init_struct_name=ctx.Struct_noun().toString();
            if(comp_name.equals(init_struct_name))
            {
                already_created_feature=true;
                if_present_first_index=i;
            }
        }

        if(already_created_feature)
        {
            StringTokenizer st = new StringTokenizer(sys_features[if_present_first_index]," ");
            String port_name=st.nextToken();
            String valid_port_name="";
            boolean has_found_valid_name=false;
            while(st.hasMoreTokens())
            {
                port_name=st.nextToken();
                if(port_name.equals("connection_to_"+ctx.struct_multinoun().getText()))
                {
                    int to_add_at_last=1;
                    while(port_name.equals("connection_to_"+ctx.struct_multinoun().getText()+to_add_at_last))
                    {
                        to_add_at_last+=1;
                    }
                    valid_port_name= "connection_to_"+ctx.struct_multinoun().getText()+to_add_at_last;
                    has_found_valid_name=true;
                }
            }
            if(has_found_valid_name)
            {
                sys_features[if_present_first_index]=sys_features[if_present_first_index]+" "+valid_port_name;
            }
            else {
                sys_features[if_present_first_index]=sys_features[if_present_first_index]+" "+"connection_to_"+ctx.struct_multinoun().getText();
            }
            StringTokenizer get_last_port=new StringTokenizer(sys_features[if_present_first_index]," ");
            String name_of_last_port="";
            while(get_last_port.hasMoreTokens())
            {
                name_of_last_port=get_last_port.nextToken();
            }
            String new_decl="";
            int second_line=1;
            for(int i=0;i<=index_names-1;i++) {
                if (ctx.Struct_noun().toString().equals(system_names[i])) {
                    for (int j = 0; j <= system_declaration[i][0].length() - 1; j++) {
                        if (system_declaration[i][0].charAt(j) == '\n' && second_line == 0) {
                            new_decl=new_decl+"\n\t\t"+name_of_last_port+" : out data port;\n";
                            second_line-=1;
                        }
                        else {
                            new_decl=new_decl+system_declaration[i][0].charAt(j);
                            if(system_declaration[i][0].charAt(j) == '\n')
                            {
                                second_line-=1;
                            }
                        }

                    }
                    system_declaration[i][0]=new_decl;
                }
            }
        }
        else {
            sys_features[index_features]=ctx.Struct_noun().toString();
//            System.out.println(ctx.struct_multinoun().getText());
            String new_decl="";
            sys_features[index_features]=sys_features[index_features]+" "+"connection_to_"+ctx.struct_multinoun().getText();
            index_features+=1;
//            System.out.println("Increased");
            int first_new_line=0;
            for(int i=0;i<=index_names-1;i++) {
                if (ctx.Struct_noun().toString().equals(system_names[i])) {
                    for (int j = 0; j <= system_declaration[i][0].length() - 1; j++) {
                        if (system_declaration[i][0].charAt(j) == '\n' && first_new_line == 0) {
                            new_decl = new_decl + "\n\tfeatures\n";
                            new_decl=new_decl+"\t\t"+"connection_to_"+ctx.struct_multinoun().getText()+" : out data port;\n";
                            first_new_line+=1;
                        }
                        else {
                            new_decl=new_decl+system_declaration[i][0].charAt(j);
                        }

                    }
                    system_declaration[i][0]=new_decl;
                }
            }
        }



        // For thr input port of the end comp
//        System.out.println("Start of second end");
        already_created_feature=false;
        if_present_first_index=-1;
        for(int i=0;i<=index_features-1;i++)
        {
            StringTokenizer st = new StringTokenizer(sys_features[i]," ");
            String comp_name=st.nextToken();
            String init_struct_name=ctx.struct_multinoun().getText();
            if(comp_name.equals(init_struct_name))
            {
                already_created_feature=true;
                if_present_first_index=i;
            }
        }
//        System.out.println(ctx.struct_multinoun().getText());
        if(already_created_feature)
        {
            StringTokenizer st = new StringTokenizer(sys_features[if_present_first_index]," ");
            String port_name=st.nextToken();
            String valid_port_name="";
            boolean has_found_valid_name=false;
//            System.out.println("haha");
            while(st.hasMoreTokens())
            {
                port_name=st.nextToken();
                if(port_name.equals("connection_from_"+ctx.Struct_noun().toString()))
                {   has_found_valid_name=true;
                    int to_add_at_last=1;
                    while(port_name.equals("connection_from_"+ctx.Struct_noun().toString()+to_add_at_last))
                    {
                        to_add_at_last+=1;
                    }
                    valid_port_name= "connection_from_"+ctx.Struct_noun().toString()+to_add_at_last;
                }
            }
//            System.out.println("hehe");
            if(has_found_valid_name)
            {
                sys_features[if_present_first_index]=sys_features[if_present_first_index]+" "+valid_port_name;
            }
            else {
                sys_features[if_present_first_index]=sys_features[if_present_first_index]+" "+"connection_from_"+ctx.Struct_noun().toString();
            }
            StringTokenizer get_last_port=new StringTokenizer(sys_features[if_present_first_index]," ");
            String name_of_last_port="";
            while(get_last_port.hasMoreTokens())
            {
                name_of_last_port=get_last_port.nextToken();
            }
//            System.out.println(name_of_last_port);
            String new_decl="";
            int second_line=1;
            for(int i=0;i<=index_names-1;i++) {
                if (ctx.struct_multinoun().getText().equals(system_names[i])) {
//                    System.out.println(ctx.struct_multinoun().getText());
                    for (int j = 0; j <= system_declaration[i][0].length() - 1; j++) {
                        if (system_declaration[i][0].charAt(j) == '\n' && second_line == 0) {
                            new_decl=new_decl+"\n\t\t"+name_of_last_port+" : in data port;\n";
                            second_line-=1;
                        }
                        else {
                            new_decl=new_decl+system_declaration[i][0].charAt(j);
                            if(system_declaration[i][0].charAt(j) == '\n')
                            {
                                second_line-=1;
                            }
                        }

                    }
                    system_declaration[i][0]=new_decl;
                }
            }
        }
        else {
            sys_features[index_features]=ctx.struct_multinoun().getText();
            String new_decl="";
            sys_features[index_features]=sys_features[index_features]+" "+"connection_from_"+ctx.Struct_noun().toString();
            index_features+=1;
            int first_new_line=0;
            for(int i=0;i<=index_names-1;i++) {
                if (ctx.struct_multinoun().getText().equals(system_names[i])) {
                    for (int j = 0; j <= system_declaration[i][0].length() - 1; j++) {
                        if (system_declaration[i][0].charAt(j) == '\n' && first_new_line == 0) {
                            new_decl = new_decl + "\n\tfeatures\n";
                            new_decl=new_decl+"\t\t"+"connection_from_"+ctx.Struct_noun().toString()+" : in data port;\n";
                            first_new_line+=1;
                        }
                        else {
                            new_decl=new_decl+system_declaration[i][0].charAt(j);
                        }

                    }
                    system_declaration[i][0]=new_decl;
                }
            }
        }




//        System.out.println(path_to_first);
//        System.out.println(path_to_second);
        StringTokenizer split_first= new StringTokenizer(path_to_first,"-");
        int number_of_int_first=0;
        while(split_first.hasMoreTokens())
        {
            String inter_sys=split_first.nextToken();
            number_of_int_first+=1;
        }

        StringTokenizer split_second= new StringTokenizer(path_to_second,"-");
        int number_of_int_second=0;
        while(split_second.hasMoreTokens())
        {
            String inter_sys=split_second.nextToken();
            number_of_int_second+=1;
        }

        int copy_of_first=number_of_int_first;
        int copy_of_second=number_of_int_second;

        split_first= new StringTokenizer(path_to_first,"-");
        split_second=new StringTokenizer(path_to_second,"-");

        //System.out.println(path_to_first);
        //System.out.println(path_to_second);
        //System.out.println(number_of_int_first);
        //System.out.println(number_of_int_second);

        if(number_of_int_first>=3)
        {
            while(copy_of_first>1)
            {
                if(copy_of_first==number_of_int_first)
                {
                    split_first.nextToken();
                    copy_of_first-=1;
                }
                else
                {
                    String name_of_comp=split_first.nextToken();
                    already_created_feature=false;
                    if_present_first_index=-1;
                    for(int i=0;i<=index_features-1;i++)
                    {
                        StringTokenizer st = new StringTokenizer(sys_features[i]," ");
                        String comp_name=st.nextToken();
                        String init_struct_name=name_of_comp;
                        if(comp_name.equals(init_struct_name))
                        {
                            already_created_feature=true;
                            if_present_first_index=i;
                        }
                    }

                    if(already_created_feature)
                    {
                        StringTokenizer st = new StringTokenizer(sys_features[if_present_first_index]," ");
                        String port_name=st.nextToken();
                        String valid_port_name="";
                        boolean has_found_valid_name=false;
                        while(st.hasMoreTokens())
                        {
                            port_name=st.nextToken();
                            if(port_name.equals("connection"+"_to_"+ctx.struct_multinoun().getText()))
                            {
                                int to_add_at_last=1;
                                while(port_name.equals("connection"+"_to_"+ctx.struct_multinoun().getText()+to_add_at_last))
                                {
                                    to_add_at_last+=1;
                                }
                                valid_port_name= "connection"+"_to_"+ctx.struct_multinoun().getText()+to_add_at_last;
                                has_found_valid_name=true;
                            }
                        }
                        if(has_found_valid_name)
                        {
                            sys_features[if_present_first_index]=sys_features[if_present_first_index]+" "+valid_port_name;
                        }
                        else {
                            sys_features[if_present_first_index]=sys_features[if_present_first_index]+" "+"connection"+"_to_"+ctx.struct_multinoun().getText();
                        }
                        StringTokenizer get_last_port=new StringTokenizer(sys_features[if_present_first_index]," ");
                        String name_of_last_port="";
                        while(get_last_port.hasMoreTokens())
                        {
                            name_of_last_port=get_last_port.nextToken();
                        }
                        String new_decl="";
                        int second_line=1;
                        for(int i=0;i<=index_names-1;i++) {
                            if (name_of_comp.equals(system_names[i])) {
                                for (int j = 0; j <= system_declaration[i][0].length() - 1; j++) {
                                    if (system_declaration[i][0].charAt(j) == '\n' && second_line == 0) {
                                        new_decl=new_decl+"\n\t\t"+name_of_last_port+" : out data port;\n";
                                        second_line-=1;
                                    }
                                    else {
                                        new_decl=new_decl+system_declaration[i][0].charAt(j);
                                        if(system_declaration[i][0].charAt(j) == '\n')
                                        {
                                            second_line-=1;
                                        }
                                    }

                                }
                                system_declaration[i][0]=new_decl;
                            }
                        }
                    }
                    else {
                        sys_features[index_features]=name_of_comp;
//            System.out.println(ctx.struct_multinoun().getText());
                        String new_decl="";
                        sys_features[index_features]=sys_features[index_features]+" "+"connection"+"_to_"+ctx.struct_multinoun().getText();
                        index_features+=1;
//            System.out.println("Increased");
                        int first_new_line=0;
                        for(int i=0;i<=index_names-1;i++) {
                            if (name_of_comp.equals(system_names[i])) {
                                for (int j = 0; j <= system_declaration[i][0].length() - 1; j++) {
                                    if (system_declaration[i][0].charAt(j) == '\n' && first_new_line == 0) {
                                        new_decl = new_decl + "\n\tfeatures\n";
                                        new_decl=new_decl+"\t\t"+"connection"+"_to_"+ctx.struct_multinoun().getText()+" : out data port;\n";
                                        first_new_line+=1;
                                    }
                                    else {
                                        new_decl=new_decl+system_declaration[i][0].charAt(j);
                                    }

                                }
                                system_declaration[i][0]=new_decl;
                            }
                        }
                    }



                    // For thr input port of the end comp
//        System.out.println("Start of second end");
                    already_created_feature=false;
                    if_present_first_index=-1;
                    for(int i=0;i<=index_features-1;i++)
                    {
                        StringTokenizer st = new StringTokenizer(sys_features[i]," ");
                        String comp_name=st.nextToken();
                        String init_struct_name=name_of_comp;
                        if(comp_name.equals(init_struct_name))
                        {
                            already_created_feature=true;
                            if_present_first_index=i;
                        }
                    }
//        System.out.println(ctx.struct_multinoun().getText());
                    if(already_created_feature)
                    {
                        StringTokenizer st = new StringTokenizer(sys_features[if_present_first_index]," ");
                        String port_name=st.nextToken();
                        String valid_port_name="";
                        boolean has_found_valid_name=false;
//            System.out.println("haha");
                        while(st.hasMoreTokens())
                        {
                            port_name=st.nextToken();
                            if(port_name.equals("connection"+"_from_"+ctx.Struct_noun().toString()))
                            {   has_found_valid_name=true;
                                int to_add_at_last=1;
                                while(port_name.equals("connection"+"_from_"+ctx.Struct_noun().toString()+to_add_at_last))
                                {
                                    to_add_at_last+=1;
                                }
                                valid_port_name= "connection"+"_from_"+ctx.Struct_noun().toString()+to_add_at_last;
                            }
                        }
//            System.out.println("hehe");
                        if(has_found_valid_name)
                        {
                            sys_features[if_present_first_index]=sys_features[if_present_first_index]+" "+valid_port_name;
                        }
                        else {
                            sys_features[if_present_first_index]=sys_features[if_present_first_index]+" "+"connection"+"_from_"+ctx.Struct_noun().toString();
                        }
                        StringTokenizer get_last_port=new StringTokenizer(sys_features[if_present_first_index]," ");
                        String name_of_last_port="";
                        while(get_last_port.hasMoreTokens())
                        {
                            name_of_last_port=get_last_port.nextToken();
                        }
//            System.out.println(name_of_last_port);
                        String new_decl="";
                        int second_line=1;
                        for(int i=0;i<=index_names-1;i++) {
                            if (name_of_comp.equals(system_names[i])) {
//                    System.out.println(ctx.struct_multinoun().getText());
                                for (int j = 0; j <= system_declaration[i][0].length() - 1; j++) {
                                    if (system_declaration[i][0].charAt(j) == '\n' && second_line == 0) {
                                        new_decl=new_decl+"\n\t\t"+name_of_last_port+" : in data port;\n";
                                        second_line-=1;
                                    }
                                    else {
                                        new_decl=new_decl+system_declaration[i][0].charAt(j);
                                        if(system_declaration[i][0].charAt(j) == '\n')
                                        {
                                            second_line-=1;
                                        }
                                    }

                                }
                                system_declaration[i][0]=new_decl;
                            }
                        }
                    }
                    else {
                        sys_features[index_features] = name_of_comp;
                        String new_decl = "";
                        sys_features[index_features] = sys_features[index_features] + " " + "connection" + "_from_" + ctx.Struct_noun().toString();
                        index_features += 1;
                        int first_new_line = 0;
                        for (int i = 0; i <= index_names - 1; i++) {
                            if (name_of_comp.equals(system_names[i])) {
                                for (int j = 0; j <= system_declaration[i][0].length() - 1; j++) {
                                    if (system_declaration[i][0].charAt(j) == '\n' && first_new_line == 0) {
                                        new_decl = new_decl + "\n\tfeatures\n";
                                        new_decl = new_decl + "\t\t" + "connection" + "_from_" + ctx.Struct_noun().toString() + " : in data port;\n";
                                        first_new_line += 1;
                                    } else {
                                        new_decl = new_decl + system_declaration[i][0].charAt(j);
                                    }

                                }
                                system_declaration[i][0] = new_decl;
                            }
                        }
                    }

                    copy_of_first-=1;
                    if(copy_of_first==1)
                    {
                        break;
                    }

                }
            }
        }

        if(number_of_int_second>=3)
        {
            while(copy_of_second>1)
            {
                if(copy_of_second==number_of_int_second)
                {
                    split_second.nextToken();
                    copy_of_second-=1;
                    //System.out.println("Skip first");
                }
                else
                {
                    String name_of_comp=split_second.nextToken();
                    //System.out.println(name_of_comp);
                    already_created_feature=false;
                    if_present_first_index=-1;
                    for(int i=0;i<=index_features-1;i++)
                    {
                        StringTokenizer st = new StringTokenizer(sys_features[i]," ");
                        String comp_name=st.nextToken();
                        String init_struct_name=name_of_comp;
                        if(comp_name.equals(init_struct_name))
                        {
                            already_created_feature=true;
                            if_present_first_index=i;
                        }
                    }

                    if(already_created_feature)
                    {
                        StringTokenizer st = new StringTokenizer(sys_features[if_present_first_index]," ");
                        String port_name=st.nextToken();
                        String valid_port_name="";
                        boolean has_found_valid_name=false;
                        while(st.hasMoreTokens())
                        {
                            port_name=st.nextToken();
                            if(port_name.equals("connection"+"_to_"+ctx.struct_multinoun().getText()))
                            {
                                int to_add_at_last=1;
                                while(port_name.equals("connection"+"_to_"+ctx.struct_multinoun().getText()+to_add_at_last))
                                {
                                    to_add_at_last+=1;
                                }
                                valid_port_name= "connection"+"_to_"+ctx.struct_multinoun().getText()+to_add_at_last;
                                has_found_valid_name=true;
                            }
                        }
                        if(has_found_valid_name)
                        {
                            sys_features[if_present_first_index]=sys_features[if_present_first_index]+" "+valid_port_name;
                        }
                        else {
                            sys_features[if_present_first_index]=sys_features[if_present_first_index]+" "+"connection"+"_to_"+ctx.struct_multinoun().getText();
                        }
                        StringTokenizer get_last_port=new StringTokenizer(sys_features[if_present_first_index]," ");
                        String name_of_last_port="";
                        while(get_last_port.hasMoreTokens())
                        {
                            name_of_last_port=get_last_port.nextToken();
                        }
                        String new_decl="";
                        int second_line=1;
                        for(int i=0;i<=index_names-1;i++) {
                            if (name_of_comp.equals(system_names[i])) {
                                for (int j = 0; j <= system_declaration[i][0].length() - 1; j++) {
                                    if (system_declaration[i][0].charAt(j) == '\n' && second_line == 0) {
                                        new_decl=new_decl+"\n\t\t"+name_of_last_port+" : out data port;\n";
                                        second_line-=1;
                                    }
                                    else {
                                        new_decl=new_decl+system_declaration[i][0].charAt(j);
                                        if(system_declaration[i][0].charAt(j) == '\n')
                                        {
                                            second_line-=1;
                                        }
                                    }

                                }
                                system_declaration[i][0]=new_decl;
                            }
                        }
                    }
                    else {
                        sys_features[index_features]=name_of_comp;
//            System.out.println(ctx.struct_multinoun().getText());
                        String new_decl="";
                        sys_features[index_features]=sys_features[index_features]+" "+"connection"+"_to_"+ctx.struct_multinoun().getText();
                        index_features+=1;
//            System.out.println("Increased");
                        int first_new_line=0;
                        for(int i=0;i<=index_names-1;i++) {
                            if (name_of_comp.equals(system_names[i])) {
                                for (int j = 0; j <= system_declaration[i][0].length() - 1; j++) {
                                    if (system_declaration[i][0].charAt(j) == '\n' && first_new_line == 0) {
                                        new_decl = new_decl + "\n\tfeatures\n";
                                        new_decl=new_decl+"\t\t"+"connection"+"_to_"+ctx.struct_multinoun().getText()+" : out data port;\n";
                                        first_new_line+=1;
                                    }
                                    else {
                                        new_decl=new_decl+system_declaration[i][0].charAt(j);
                                    }

                                }
                                system_declaration[i][0]=new_decl;
                            }
                        }
                    }



                    // For thr input port of the end comp
//        System.out.println("Start of second end");
                    already_created_feature=false;
                    if_present_first_index=-1;
                    for(int i=0;i<=index_features-1;i++)
                    {
                        StringTokenizer st = new StringTokenizer(sys_features[i]," ");
                        String comp_name=st.nextToken();
                        String init_struct_name=name_of_comp;
                        if(comp_name.equals(init_struct_name))
                        {
                            already_created_feature=true;
                            if_present_first_index=i;
                        }
                    }
//        System.out.println(ctx.struct_multinoun().getText());
                    if(already_created_feature)
                    {
                        StringTokenizer st = new StringTokenizer(sys_features[if_present_first_index]," ");
                        String port_name=st.nextToken();
                        String valid_port_name="";
                        boolean has_found_valid_name=false;
//            System.out.println("haha");
                        while(st.hasMoreTokens())
                        {
                            port_name=st.nextToken();
                            if(port_name.equals("connection"+"_from_"+ctx.Struct_noun().toString()))
                            {   has_found_valid_name=true;
                                int to_add_at_last=1;
                                while(port_name.equals("connection"+"_from_"+ctx.Struct_noun().toString()+to_add_at_last))
                                {
                                    to_add_at_last+=1;
                                }
                                valid_port_name= "connection"+"_from_"+ctx.Struct_noun().toString()+to_add_at_last;
                            }
                        }
//            System.out.println("hehe");
                        if(has_found_valid_name)
                        {
                            sys_features[if_present_first_index]=sys_features[if_present_first_index]+" "+valid_port_name;
                        }
                        else {
                            sys_features[if_present_first_index]=sys_features[if_present_first_index]+" "+"connection"+"_from_"+ctx.Struct_noun().toString();
                        }
                        StringTokenizer get_last_port=new StringTokenizer(sys_features[if_present_first_index]," ");
                        String name_of_last_port="";
                        while(get_last_port.hasMoreTokens())
                        {
                            name_of_last_port=get_last_port.nextToken();
                        }
//            System.out.println(name_of_last_port);
                        String new_decl="";
                        int second_line=1;
                        for(int i=0;i<=index_names-1;i++) {
                            if (name_of_comp.equals(system_names[i])) {
//                    System.out.println(ctx.struct_multinoun().getText());
                                for (int j = 0; j <= system_declaration[i][0].length() - 1; j++) {
                                    if (system_declaration[i][0].charAt(j) == '\n' && second_line == 0) {
                                        new_decl=new_decl+"\n\t\t"+name_of_last_port+" : in data port;\n";
                                        second_line-=1;
                                    }
                                    else {
                                        new_decl=new_decl+system_declaration[i][0].charAt(j);
                                        if(system_declaration[i][0].charAt(j) == '\n')
                                        {
                                            second_line-=1;
                                        }
                                    }

                                }
                                system_declaration[i][0]=new_decl;
                            }
                        }
                    }
                    else {
                        sys_features[index_features] = name_of_comp;
                        String new_decl = "";
                        sys_features[index_features] = sys_features[index_features] + " " + "connection" + "_from_" + ctx.Struct_noun().toString();
                        index_features += 1;
                        int first_new_line = 0;
                        for (int i = 0; i <= index_names - 1; i++) {
                            if (name_of_comp.equals(system_names[i])) {
                                for (int j = 0; j <= system_declaration[i][0].length() - 1; j++) {
                                    if (system_declaration[i][0].charAt(j) == '\n' && first_new_line == 0) {
                                        new_decl = new_decl + "\n\tfeatures\n";
                                        new_decl = new_decl + "\t\t" + "connection" + "_from_" + ctx.Struct_noun().toString() + " : in data port;\n";
                                        first_new_line += 1;
                                    } else {
                                        new_decl = new_decl + system_declaration[i][0].charAt(j);
                                    }

                                }
                                system_declaration[i][0] = new_decl;
                            }
                        }
                    }

                    copy_of_second-=1;
                    if(copy_of_second==1)
                    {
                        break;
                    }

                }
            }

        }


        //starting to make connection between the components
        if(under_same_component)
        {
            int index_of_upper_comp=-1;
            String upper_comp="";
            for(int i=0;i<=index_subcomponents-1;i++)
            {
                if(system_subcomponents[i].contains(ctx.Struct_noun().toString()) && system_subcomponents[i].contains(ctx.struct_multinoun().Struct_noun(0).getText()))
                {
                    StringTokenizer st=new StringTokenizer(system_subcomponents[i]," ");
                    upper_comp=st.nextToken();
                }
            }
            for(int i=0; i<=index_names-1;i++)
            {
                if(system_names[i].equals(upper_comp))
                {
                    index_of_upper_comp=i;
                }
            }
            int count_of_connections=0;
            boolean already_connections=false;
            for(int i=0;i<=index_connections-1;i++)
            {
                if(connections[i].equals(upper_comp))
                {
                    already_connections=true;
                    count_of_connections=number_of_connections[i];
                }
            }
            String new_decl="";
            int nextline_char=0;
            String port_name_of_first="";
            String port_name_of_second="";

            for(int i=0;i<=index_features-1;i++)
            {
                StringTokenizer st= new StringTokenizer(sys_features[i]," ");
                String name_of_comp_having_features=st.nextToken();
                if(ctx.Struct_noun().toString().equals(name_of_comp_having_features))
                {
                    while(st.hasMoreTokens())
                    {
                        port_name_of_first=st.nextToken();
                    }
                }
                else if(ctx.struct_multinoun().Struct_noun(0).getText().equals(name_of_comp_having_features))
                {
                    while(st.hasMoreTokens())
                    {
                        port_name_of_second=st.nextToken();
                    }
                }
                else {

                }
            }

            for(int i=system_declaration[index_of_upper_comp][1].length()-1; i>=0; i--)
            {
                if(system_declaration[index_of_upper_comp][1].charAt(i)=='\n' && nextline_char==1)
                {
                    if(already_connections)
                    {
                        for(int k=0;k<=index_connections-1;k++)
                        {
                            if(upper_comp.equals(connections[k]))
                            {
                                number_of_connections[k]+=1;
                                new_decl="\n\t\t"+upper_comp+count_of_connections+": port "+"this_"+ctx.Struct_noun().toString()+"."+port_name_of_first+"->this_"+ctx.struct_multinoun().Struct_noun(0).getText()+"."+port_name_of_second+";\n" + new_decl;
                                update_list_flows(upper_comp, ctx.Struct_noun().toString(), port_name_of_first , ctx.struct_multinoun().Struct_noun(0).getText(), port_name_of_second);
                                break;
                            }
                        }
                    }
                    else {
                        connections[index_connections]=upper_comp;
                        index_connections+=1;
                        number_of_connections[index_connections-1]=1;
                        new_decl="\n\tconnections\n"+"\t\t"+upper_comp+"0"+": port "+"this_"+ctx.Struct_noun().toString()+"."+port_name_of_first+"->this_"+ctx.struct_multinoun().Struct_noun(0).getText()+"."+port_name_of_second+";\n" + new_decl;
                        update_list_flows(upper_comp, ctx.Struct_noun().toString(), port_name_of_first ,ctx.struct_multinoun().Struct_noun(0).getText(), port_name_of_second);
                    }
                    nextline_char+=1;
                }
                else
                {
                    if(system_declaration[index_of_upper_comp][1].charAt(i)=='\n')
                    {
                        nextline_char+=1;
                    }
                    new_decl=system_declaration[index_of_upper_comp][1].charAt(i)+new_decl;
                }
            }
            system_declaration[index_of_upper_comp][1]=new_decl;
        }
        else {

            //System.out.println(path_to_first);
            //System.out.println(path_to_second);


            number_of_int_first = 0;
            number_of_int_second = 0;

            split_first = new StringTokenizer(path_to_first, "-");
            split_second = new StringTokenizer(path_to_second, "-");

            while (split_first.hasMoreTokens()) {
                number_of_int_first += 1;
                split_first.nextToken();
            }

            while (split_second.hasMoreTokens()) {
                number_of_int_second += 1;
                split_second.nextToken();
            }

            //For intermediate systems within the path of first and second
            if (number_of_int_first >= 3) {
                String ind_sys_first[] = new String[number_of_int_first - 1];
                int index_of_ind_sys_first = 0;
                split_first = new StringTokenizer(path_to_first, "-");
                split_first.nextToken();
                while (split_first.hasMoreTokens()) {
                    ind_sys_first[index_of_ind_sys_first] = split_first.nextToken();
                    index_of_ind_sys_first += 1;
                }
                String upper_comp = "";
                for (int k = 0; k <= index_of_ind_sys_first - 2; k++) {
                    for (int l = 0; l <= index_subcomponents - 1; l++) {
                        if (system_subcomponents[l].contains(ind_sys_first[k]) && system_subcomponents[l].contains(ind_sys_first[k + 1])) {
                            StringTokenizer st = new StringTokenizer(system_subcomponents[l], " ");
                            upper_comp = st.nextToken();
                        }
                    }

                    int index_of_upper_comp = -1;
                    for (int i = 0; i <= index_names - 1; i++) {
                        if (system_names[i].equals(upper_comp)) {
                            index_of_upper_comp = i;
                        }
                    }
                    int count_of_connections = 0;
                    boolean already_connections = false;
                    for (int i = 0; i <= index_connections - 1; i++) {
                        if (connections[i].equals(upper_comp)) {
                            already_connections = true;
                            count_of_connections = number_of_connections[i];
                        }
                    }
                    String new_decl = "";
                    int nextline_char = 0;
                    String port_name_of_first = "";
                    String port_name_of_second = "";

                    for (int i = 0; i <= index_features - 1; i++) {
                        StringTokenizer st = new StringTokenizer(sys_features[i], " ");
                        String name_of_comp_having_features = st.nextToken();
                        String second_last_comp = "";
                        if (ind_sys_first[k].equals(name_of_comp_having_features)) {
                            while (st.hasMoreTokens()) {
                                port_name_of_first = st.nextToken();
                            }
                        } else if (ind_sys_first[k + 1].equals(name_of_comp_having_features)) {
                            while (st.hasMoreTokens()) {
                                second_last_comp = port_name_of_second;
                                port_name_of_second = st.nextToken();
                            }
                            if(k==index_of_ind_sys_first-2)
                            {

                            }
                            else {
                                port_name_of_second=second_last_comp;
                            }
                        } else {

                        }
                    }

                    for (int i = system_declaration[index_of_upper_comp][1].length() - 1; i >= 0; i--) {
                        if (system_declaration[index_of_upper_comp][1].charAt(i) == '\n' && nextline_char == 1) {
                            if (already_connections) {
                                for (int m = 0; m <= index_connections - 1; m++) {
                                    if (upper_comp.equals(connections[m])) {
                                        number_of_connections[m] += 1;
                                        new_decl = "\n\t\t" + upper_comp + count_of_connections + ": port " + "this_" + ind_sys_first[k+1] + "." + port_name_of_second + "->this_" + ind_sys_first[k] + "." + port_name_of_first + ";\n" + new_decl;
                                        update_list_flows(upper_comp, ind_sys_first[k], port_name_of_first ,ind_sys_first[k+1], port_name_of_second);
                                        break;
                                    }
                                }
                            } else {
                                connections[index_connections] = upper_comp;
                                index_connections += 1;
                                number_of_connections[index_connections - 1] = 1;
                                new_decl = "\n\tconnections\n" + "\t\t" + upper_comp + "0" + ": port " + "this_" + ind_sys_first[k+1] + "." + port_name_of_second + "->this_" + ind_sys_first[k] + "." + port_name_of_first + ";\n" + new_decl;
                                update_list_flows(upper_comp, ind_sys_first[k], port_name_of_first ,ind_sys_first[k+1], port_name_of_second);
                            }
                            nextline_char += 1;
                        } else {
                            if (system_declaration[index_of_upper_comp][1].charAt(i) == '\n') {
                                nextline_char += 1;
                            }
                            new_decl = system_declaration[index_of_upper_comp][1].charAt(i) + new_decl;
                        }
                    }
                    system_declaration[index_of_upper_comp][1] = new_decl;
//                    System.out.println(system_declaration[index_of_upper_comp][1]);

                }
            }
            if (number_of_int_second >= 3) {
                String ind_sys_second[] = new String[number_of_int_second - 1];
                int index_of_ind_sys_second = 0;
                split_second = new StringTokenizer(path_to_second, "-");
                split_second.nextToken();
                while (split_second.hasMoreTokens()) {
                    ind_sys_second[index_of_ind_sys_second] = split_second.nextToken();
                    index_of_ind_sys_second += 1;
                }

                String upper_comp = "";
                for (int k = 0; k <= index_of_ind_sys_second - 2; k++) {
                    for (int l = 0; l <= index_subcomponents - 1; l++) {
                        if (system_subcomponents[l].contains(ind_sys_second[k]) && system_subcomponents[l].contains(ind_sys_second[k + 1])) {
                            StringTokenizer st = new StringTokenizer(system_subcomponents[l], " ");
                            upper_comp = st.nextToken();
                        }
                    }

                    int index_of_upper_comp = -1;
                    for (int i = 0; i <= index_names - 1; i++) {
                        if (system_names[i].equals(upper_comp)) {
                            index_of_upper_comp = i;
                        }
                    }
                    int count_of_connections = 0;
                    boolean already_connections = false;
                    for (int i = 0; i <= index_connections - 1; i++) {
                        if (connections[i].equals(upper_comp)) {
                            already_connections = true;
                            count_of_connections = number_of_connections[i];
                        }
                    }
                    String new_decl = "";
                    int nextline_char = 0;
                    String port_name_of_first = "";
                    String port_name_of_second = "";

                    for (int i = 0; i <= index_features - 1; i++) {
                        StringTokenizer st = new StringTokenizer(sys_features[i], " ");
                        String name_of_comp_having_features = st.nextToken();
                        String second_last_port="";
                        if (ind_sys_second[k].equals(name_of_comp_having_features)) {
                            while (st.hasMoreTokens()) {
                                second_last_port=port_name_of_first;
                                port_name_of_first = st.nextToken();
                            }
                            port_name_of_first=second_last_port;
                        } else if (ind_sys_second[k + 1].equals(name_of_comp_having_features)) {
                            while (st.hasMoreTokens()) {
                                port_name_of_second = st.nextToken();
                            }
                        } else {

                        }
                    }

                    for (int i = system_declaration[index_of_upper_comp][1].length() - 1; i >= 0; i--) {
                        if (system_declaration[index_of_upper_comp][1].charAt(i) == '\n' && nextline_char == 1) {
                            if (already_connections) {
                                for (int m = 0; m <= index_connections - 1; m++) {
                                    if (upper_comp.equals(connections[m])) {
                                        number_of_connections[m] += 1;
                                        new_decl = "\n\t\t" + upper_comp + count_of_connections + ": port " + "this_" + ind_sys_second[k] + "." + port_name_of_first + "->this_" + ind_sys_second[k + 1] + "." + port_name_of_second + ";\n" + new_decl;
                                        update_list_flows(upper_comp, ind_sys_second[k], port_name_of_first ,ind_sys_second[k+1], port_name_of_second);
                                        break;
                                    }
                                }
                            } else {
                                connections[index_connections] = upper_comp;
                                index_connections += 1;
                                number_of_connections[index_connections - 1] = 1;
                                new_decl = "\n\tconnections\n" + "\t\t" + upper_comp + "0" + ": port " + "this_" + ind_sys_second[k] + "." + port_name_of_first + "->this_" + ind_sys_second[k + 1] + "." + port_name_of_second + ";\n" + new_decl;
                                update_list_flows(upper_comp, ind_sys_second[k], port_name_of_first ,ind_sys_second[k+1], port_name_of_second);
                            }
                            nextline_char += 1;
                        } else {
                            if (system_declaration[index_of_upper_comp][1].charAt(i) == '\n') {
                                nextline_char += 1;
                            }
                            new_decl = system_declaration[index_of_upper_comp][1].charAt(i) + new_decl;
                        }
                    }
                    system_declaration[index_of_upper_comp][1] = new_decl;
                    //System.out.println(system_declaration[index_of_upper_comp][1]);

                }
            }


            //For connections between first of p1 and first of p2
            String comm_conn_first = "";
            String comm_conn_second = "";
            split_first = new StringTokenizer(path_to_first, "-");
            split_second = new StringTokenizer(path_to_second, "-");
            split_first.nextToken();
            comm_conn_first = split_first.nextToken();
            split_second.nextToken();
            comm_conn_second = split_second.nextToken();

            int index_of_upper_comp=-1;
            String upper_comp="";
            for(int i=0;i<=index_subcomponents-1;i++)
            {
                if(system_subcomponents[i].contains(comm_conn_first) && system_subcomponents[i].contains(comm_conn_second))
                {
                    StringTokenizer st=new StringTokenizer(system_subcomponents[i]," ");
                    upper_comp=st.nextToken();
                }
            }
            for(int i=0; i<=index_names-1;i++)
            {
                if(system_names[i].equals(upper_comp))
                {
                    index_of_upper_comp=i;
                }
            }
            int count_of_connections=0;
            boolean already_connections=false;
            for(int i=0;i<=index_connections-1;i++)
            {
                if(connections[i].equals(upper_comp))
                {
                    already_connections=true;
                    count_of_connections=number_of_connections[i];
                }
            }
            String new_decl="";
            int nextline_char=0;
            String port_name_of_first="";
            String port_name_of_second="";

            for(int i=0;i<=index_features-1;i++)
            {
                StringTokenizer st= new StringTokenizer(sys_features[i]," ");
                String name_of_comp_having_features=st.nextToken();
                String second_last_conn="";
                if(comm_conn_first.equals(name_of_comp_having_features))
                {
                    while(st.hasMoreTokens())
                    {
                        second_last_conn=port_name_of_first;
                        port_name_of_first=st.nextToken();
                    }
                    if(number_of_int_first>=3)
                    {
                        port_name_of_first=second_last_conn;
                    }
                }
                else if(comm_conn_second.equals(name_of_comp_having_features))
                {
                    while(st.hasMoreTokens())
                    {
                        port_name_of_second=st.nextToken();
                    }
                }
                else {

                }
            }


            for(int i=system_declaration[index_of_upper_comp][1].length()-1; i>=0; i--)
            {
                if(system_declaration[index_of_upper_comp][1].charAt(i)=='\n' && nextline_char==1)
                {
                    if(already_connections)
                    {
                        for(int k=0;k<=index_connections-1;k++)
                        {
                            if(upper_comp.equals(connections[k]))
                            {
                                number_of_connections[k]+=1;
                                new_decl="\n\t\t"+upper_comp+count_of_connections+": port "+"this_"+comm_conn_first+"."+port_name_of_first+"->this_"+comm_conn_second+"."+port_name_of_second+";\n" + new_decl;
                                update_list_flows(upper_comp, comm_conn_first, port_name_of_first ,comm_conn_second, port_name_of_second);
                                break;
                            }
                        }
                    }
                    else {
                        connections[index_connections]=upper_comp;
                        index_connections+=1;
                        number_of_connections[index_connections-1]=1;
                        new_decl="\n\tconnections\n"+"\t\t"+upper_comp+"0"+": port "+"this_"+comm_conn_first+"."+port_name_of_first+"->this_"+comm_conn_second+"."+port_name_of_second+";\n" + new_decl;
                        update_list_flows(upper_comp, comm_conn_first, port_name_of_first ,comm_conn_second, port_name_of_second);
                    }
                    nextline_char+=1;
                }
                else
                {
                    if(system_declaration[index_of_upper_comp][1].charAt(i)=='\n')
                    {
                        nextline_char+=1;
                    }
                    new_decl=system_declaration[index_of_upper_comp][1].charAt(i)+new_decl;
                }
            }
            system_declaration[index_of_upper_comp][1]=new_decl;

        }

        return null;
    }

    /**
     * Visit a parse tree produced by {@link New_grammarParser#functional_stmts}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    @Override
    public Object visitFunctional_stmts(New_grammarParser.Functional_stmtsContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * Visit a parse tree produced by {@link New_grammarParser#functional_stmt}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    @Override
    public Object visitFunctional_stmt(New_grammarParser.Functional_stmtContext ctx) {

        String func_verb_of_stmt = ctx.Func_verb().toString();
        String list_of_all_flows="";
//        System.out.println(ctx.getText());
        //Getting names of all the flows present in the statement
        if(func_verb_of_stmt.equals("energizes")||func_verb_of_stmt.equals("deenergizes"))
        {
            list_of_all_flows=list_of_all_flows+" "+ctx.flow(0).getText().toString();
        }
        else {
            String multf = ctx.multi_flow(0).getText();
            if (multf.contains("and") && multf.contains(",")) {
                StringTokenizer st = new StringTokenizer(multf, ",");
                while (st.hasMoreTokens()) {
                    String token_name = st.nextToken();
                    if (token_name.contains("and")) {
                        int index_of_and = multf.indexOf("and");
                        String first_flow = multf.substring(0, index_of_and);
                        String second_flow = multf.substring(index_of_and + 3);
                        list_of_all_flows = list_of_all_flows + " " + first_flow + " " + second_flow;
                    } else {
                        list_of_all_flows = list_of_all_flows + " " + token_name;
                    }
                }
            } else if (multf.contains("and")) {
                int index_of_and = multf.indexOf("and");
                String first_flow = multf.substring(0, index_of_and);
                String second_flow = multf.substring(index_of_and + 3);
                list_of_all_flows = list_of_all_flows + " " + first_flow + " " + second_flow;
            } else {
                list_of_all_flows = list_of_all_flows + " " + multf;
            }
        }

        //System.out.println("All flows: "+list_of_all_flows);
        boolean flow_has_energy= list_of_all_flows.contains("energy");

//        System.out.println(ctx.multi_flow(0).getText());
//        System.out.println(ctx.multi_flow(1).getText());

        if(func_verb_of_stmt.equals("energizes"))
        {
            //------------No ports--------------------//
        }
        else if(func_verb_of_stmt.equals("deenergizes"))
        {
            //------------No ports---------------------//
        }
        else if(func_verb_of_stmt.equals("separates"))
        {
            if(!flow_has_energy)
            {
                if(!ctx.multi_flow(0).getText().equals(ctx.multi_flow(1).getText()))
                {
                    //-------No ports--------------//
                }
            }
        }
        else if(func_verb_of_stmt.equals("distributes"))
        {
            if(flow_has_energy)
            {
                String ptf[]=new String[2];
                String pts[]= new String[2];
                boolean under_same_comp[]=new boolean[2];
                if(ctx.struct_multinoun().getText().contains("and") && (!ctx.struct_multinoun().getText().contains(",")));
                {   //System.out.println("Into the system");
                    String list_of_comp= ctx.struct_multinoun().getText();
                    int index_of_and = list_of_comp.indexOf("and");
                    String first_comp = list_of_comp.substring(0, index_of_and);
                    String second_comp = list_of_comp.substring(index_of_and + 3);
//                    System.out.println(ctx.struct_multinoun().Struct_noun(0).getText());
//                    System.out.println(ctx.struct_multinoun().Struct_noun(1).getText());
                    int index_of_comp_already_created=-1;
                    boolean already_created=false;

//                    System.out.println(ctx.multi_flow(0).getText());
                    //Output ports for the first comp
                    for(int i=0;i<=index_features-1;i++)
                    {
                        StringTokenizer st=new StringTokenizer(sys_features[i]," ");
                        String comp_name=st.nextToken();
//                        System.out.println(ctx.Struct_noun(0).toString());
//                        System.out.println(comp_name);
                        if(comp_name.equals(ctx.Struct_noun(0).toString()))
                        {
                            already_created=true;
                            index_of_comp_already_created=i;
                            //System.out.println(index_of_comp_already_created);
                            break;
                        }
                    }
//                    System.out.println(already_created);
//                    System.out.println(ctx.getText());
                    int copy_of_index_of_comp_already_created=index_of_comp_already_created;
                    int index_of_comp_later=0;
                    if(already_created)
                    {
                        while(index_of_comp_later<=1) {
                            index_of_comp_already_created=copy_of_index_of_comp_already_created;
                            String path_to_first=ctx.Struct_noun(0).toString();
                            String path_to_second="";
                            boolean under_same_component=false;
                            for(int i=0;i<=index_subcomponents-1;i++)
                            {
                                if(system_subcomponents[i].contains(ctx.Struct_noun(0).toString()) && system_subcomponents[i].contains(ctx.struct_multinoun().Struct_noun(index_of_comp_later).getText()))
                                {
                                    under_same_component=true;
                                    under_same_comp[index_of_comp_later]=under_same_component;
                                    path_to_second=ctx.struct_multinoun().Struct_noun(index_of_comp_later).getText();
//                                   //System.out.println("Under same component");
                                }

                            }
                            if(!under_same_component) {
                                int index_of_first_comp_upper = -1;
                                boolean found_it = false;
                                int index_of_second_comp_upper = -1;
                                for (int i = 0; i <= index_subcomponents - 1; i++) {
                                    if (system_subcomponents[i].contains(ctx.struct_multinoun().Struct_noun(index_of_comp_later).getText())) {
                                        index_of_second_comp_upper = i;
                                    }
                                    if (system_subcomponents[i].contains(ctx.Struct_noun(index_of_comp_later).toString())) {
                                        index_of_first_comp_upper = i;
                                    }
                                }

                                int copy_of_second_comp = index_of_second_comp_upper;
                                String second = ctx.struct_multinoun().Struct_noun(index_of_comp_later).getText();
                                while (index_of_first_comp_upper >= 0) {
                                    StringTokenizer st = new StringTokenizer(system_subcomponents[index_of_first_comp_upper], " ");
                                    String first = st.nextToken();
                                    //System.out.println(path_to_first.contains(first));
                                    //System.out.println(path_to_first);
                                    if(first.equals(path_to_first))
                                    {

                                    }
                                    else {
                                        path_to_first = first + "-" + path_to_first;
                                    }
                                    second = ctx.struct_multinoun().Struct_noun(index_of_comp_later).getText();
                                    path_to_second = second;
                                    for (int i = 0; i <= index_subcomponents - 1; i++) {
                                        if (system_subcomponents[i].contains(ctx.struct_multinoun().Struct_noun(index_of_comp_later).getText())) {
                                            index_of_second_comp_upper = i;
                                        }
                                    }
                                    while (index_of_second_comp_upper >= 0) {
                                        //System.out.println(first);
                                        //System.out.println(second);
                                        for (int i = 0; i <= index_subcomponents - 1; i++) {
                                            if (system_subcomponents[i].contains(first) && system_subcomponents[i].contains(second)) {
                                                StringTokenizer st3 = new StringTokenizer(system_subcomponents[i], " ");
                                                String common_comp_for_conn = st3.nextToken();

                                                if(path_to_first.contains(common_comp_for_conn))
                                                {

                                                }
                                                else {
                                                    path_to_first = common_comp_for_conn + "-" + path_to_first;
                                                }
                                                if(path_to_second.contains(common_comp_for_conn))
                                                {

                                                }
                                                else {
                                                    path_to_second = common_comp_for_conn + "-" + path_to_second;
                                                }
                                                found_it = true;
                                                break;
                                            }
                                        }
                                        if (found_it) {
                                            break;
                                        } else {
                                            if (index_of_second_comp_upper == 0) {
                                                break;
                                            }
                                            for (int j = 0; j <= index_of_second_comp_upper; j++) {
                                                boolean val=false;
                                                if (system_subcomponents[j].contains(second)) {
                                                    //System.out.println("Outer second");
                                                    index_of_second_comp_upper = j;
                                                    StringTokenizer st2 = new StringTokenizer(system_subcomponents[index_of_second_comp_upper], " ");
                                                    String new_second = st2.nextToken();
                                                    if(new_second.equals(second))
                                                    {
                                                        for (int k = 0; k <= index_of_second_comp_upper-1; k++) {
                                                            //System.out.println("Inner second");
                                                            if (system_subcomponents[k].contains(second)) {
                                                                index_of_second_comp_upper = k;
                                                                st2 = new StringTokenizer(system_subcomponents[index_of_second_comp_upper], " ");
                                                                second = st2.nextToken();
                                                                path_to_second = second + "-" + path_to_second;
                                                                val=true;
                                                                break;
                                                            }
                                                        }
                                                    }
                                                    else {
                                                        second=new_second;
                                                        path_to_second = second + "-" + path_to_second;
                                                        val=true;
                                                        break;
                                                    }
                                                    if(val)
                                                    {
                                                        break;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    if (found_it) {
                                        break;
                                    } else {
                                        if (index_of_first_comp_upper == 0) {
                                            break;
                                        }
                                        for (int j = 0; j <= index_of_first_comp_upper; j++) {
                                            //System.out.println("Outer first");
                                            boolean val=false;
                                            if (system_subcomponents[j].contains(first)) {
                                                index_of_first_comp_upper = j;
                                                StringTokenizer st2 = new StringTokenizer(system_subcomponents[index_of_first_comp_upper], " ");
                                                String new_first = st2.nextToken();
                                                if(first.equals(new_first)) {
                                                    for (int k = 0; k <= index_of_first_comp_upper-1; k++) {
                                                        //System.out.println("Inner first");
                                                        if (system_subcomponents[k].contains(first)) {
                                                            index_of_first_comp_upper = k;
                                                            val=true;
                                                            break;
                                                        }
                                                    }
                                                }
                                                else{
                                                    val=true;
                                                    break;
                                                }
                                                if(val)
                                                {
                                                    break;
                                                }
                                            }
                                        }
                                    }
                                }

                            }

                           // System.out.println(path_to_first);
                            //System.out.println(path_to_second);
                            ptf[index_of_comp_later]=path_to_first;
                            pts[index_of_comp_later]=path_to_second;

//                        System.out.println(index_of_comp_later);
                            //System.out.println(index_of_comp_already_created);
                        StringTokenizer st = new StringTokenizer(sys_features[index_of_comp_already_created], " ");
                        String port_name = st.nextToken();
                        String valid_port_name = "";
                        boolean has_found_valid_name = false;
                        while (st.hasMoreTokens()) {
                            port_name=st.nextToken();
                            if (port_name.equals(ctx.multi_flow(0).getText()+"_to_" + ctx.struct_multinoun().Struct_noun(index_of_comp_later).getText())) {
                                int to_add_at_last = 1;
                                while (port_name.equals(ctx.multi_flow(0).getText()+"_to_" + ctx.struct_multinoun().Struct_noun(index_of_comp_later).getText() + to_add_at_last)) {
                                    to_add_at_last += 1;
                                }
                                valid_port_name = ctx.multi_flow(0).getText()+"_to_" + ctx.struct_multinoun().Struct_noun(index_of_comp_later).getText() + to_add_at_last;
                                has_found_valid_name = true;
                            }
                        }
                        if (has_found_valid_name) {
                            sys_features[index_of_comp_already_created] = sys_features[index_of_comp_already_created] + " " + valid_port_name;
                        }
                        else {
                            sys_features[index_of_comp_already_created] = sys_features[index_of_comp_already_created] + " " + ctx.multi_flow(0).getText()+"_to_" + ctx.struct_multinoun().Struct_noun(index_of_comp_later).getText();
                        }
                        StringTokenizer get_last_port = new StringTokenizer(sys_features[index_of_comp_already_created], " ");
                        String name_of_last_port = "";
                        while (get_last_port.hasMoreTokens()) {
                            name_of_last_port = get_last_port.nextToken();
                        }
//                        System.out.println(name_of_last_port+" : After while");
                        String new_decl = "";
                        int second_line = 1;
                        for (int i = 0; i <= index_names - 1; i++) {
                            if (ctx.Struct_noun(0).toString().equals(system_names[i])) {
                                for (int j = 0; j <= system_declaration[i][0].length() - 1; j++) {
                                    if (system_declaration[i][0].charAt(j) == '\n' && second_line == 0) {
                                        new_decl = new_decl + "\n\t\t" + name_of_last_port + " : out data port;\n";
//                                        System.out.println(name_of_last_port);
                                        second_line -= 1;
                                    } else {
                                        new_decl = new_decl + system_declaration[i][0].charAt(j);
                                        if (system_declaration[i][0].charAt(j) == '\n') {
                                            second_line -= 1;
                                        }
                                    }

                                }
                                system_declaration[i][0] = new_decl;
                            }
                        }



                            StringTokenizer split_first= new StringTokenizer(path_to_first,"-");
                            int number_of_int_first=0;
                            while(split_first.hasMoreTokens())
                            {
                                String inter_sys=split_first.nextToken();
                                number_of_int_first+=1;
                            }

                            StringTokenizer split_second= new StringTokenizer(path_to_second,"-");
                            int number_of_int_second=0;
                            while(split_second.hasMoreTokens())
                            {
                                String inter_sys=split_second.nextToken();
                                number_of_int_second+=1;
                            }

                            int copy_of_first=number_of_int_first;
                            int copy_of_second=number_of_int_second;

                            split_first= new StringTokenizer(path_to_first,"-");
                            split_second=new StringTokenizer(path_to_second,"-");

                            //System.out.println(path_to_first);
                            //System.out.println(path_to_second);
                            //System.out.println(number_of_int_first);
                            //System.out.println(number_of_int_second);

                            if(number_of_int_first>=3)
                            {
                                while(copy_of_first>1)
                                {
                                    if(copy_of_first==number_of_int_first)
                                    {
                                        split_first.nextToken();
                                        copy_of_first-=1;
                                    }
                                    else
                                    {
                                        String name_of_comp=split_first.nextToken();
                                        boolean already_created_feature=false;
                                        int if_present_first_index=-1;
                                        for(int i=0;i<=index_features-1;i++)
                                        {
                                            StringTokenizer st6 = new StringTokenizer(sys_features[i]," ");
                                            String comp_name=st6.nextToken();
                                            String init_struct_name=name_of_comp;
                                            if(comp_name.equals(init_struct_name))
                                            {
                                                already_created_feature=true;
                                                if_present_first_index=i;
                                            }
                                        }

                                        if(already_created_feature)
                                        {
                                            StringTokenizer st6 = new StringTokenizer(sys_features[if_present_first_index]," ");
                                            port_name=st6.nextToken();
                                            valid_port_name="";
                                            has_found_valid_name=false;
                                            while(st6.hasMoreTokens())
                                            {
                                                port_name=st6.nextToken();
                                                if(port_name.equals(ctx.multi_flow(0).getText()+"_to_"+ctx.struct_multinoun().Struct_noun(index_of_comp_later).getText()))
                                                {
                                                    int to_add_at_last=1;
                                                    while(port_name.equals(ctx.multi_flow(0).getText()+"_to_"+ctx.struct_multinoun().Struct_noun(index_of_comp_later).getText()+to_add_at_last))
                                                    {
                                                        to_add_at_last+=1;
                                                    }
                                                    valid_port_name= ctx.multi_flow(0).getText()+"_to_"+ctx.struct_multinoun().Struct_noun(index_of_comp_later).getText()+to_add_at_last;
                                                    has_found_valid_name=true;
                                                }
                                            }
                                            if(has_found_valid_name)
                                            {
                                                sys_features[if_present_first_index]=sys_features[if_present_first_index]+" "+valid_port_name;
                                            }
                                            else {
                                                sys_features[if_present_first_index]=sys_features[if_present_first_index]+" "+ctx.multi_flow(0).getText()+"_to_"+ctx.struct_multinoun().Struct_noun(index_of_comp_later).getText();
                                            }
                                            get_last_port=new StringTokenizer(sys_features[if_present_first_index]," ");
                                            name_of_last_port="";
                                            while(get_last_port.hasMoreTokens())
                                            {
                                                name_of_last_port=get_last_port.nextToken();
                                            }
                                            new_decl="";
                                            second_line=1;
                                            for(int i=0;i<=index_names-1;i++) {
                                                if (name_of_comp.equals(system_names[i])) {
                                                    for (int j = 0; j <= system_declaration[i][0].length() - 1; j++) {
                                                        if (system_declaration[i][0].charAt(j) == '\n' && second_line == 0) {
                                                            new_decl=new_decl+"\n\t\t"+name_of_last_port+" : out data port;\n";
                                                            second_line-=1;
                                                        }
                                                        else {
                                                            new_decl=new_decl+system_declaration[i][0].charAt(j);
                                                            if(system_declaration[i][0].charAt(j) == '\n')
                                                            {
                                                                second_line-=1;
                                                            }
                                                        }

                                                    }
                                                    system_declaration[i][0]=new_decl;
                                                }
                                            }
                                        }
                                        else {
                                            sys_features[index_features]=name_of_comp;
//            System.out.println(ctx.struct_multinoun().getText());
                                            new_decl="";
                                            sys_features[index_features]=sys_features[index_features]+" "+ctx.multi_flow(0).getText()+"_to_"+ctx.struct_multinoun().Struct_noun(index_of_comp_later).getText();
                                            index_features+=1;
//            System.out.println("Increased");
                                            int first_new_line=0;
                                            for(int i=0;i<=index_names-1;i++) {
                                                if (name_of_comp.equals(system_names[i])) {
                                                    for (int j = 0; j <= system_declaration[i][0].length() - 1; j++) {
                                                        if (system_declaration[i][0].charAt(j) == '\n' && first_new_line == 0) {
                                                            new_decl = new_decl + "\n\tfeatures\n";
                                                            new_decl=new_decl+"\t\t"+ctx.multi_flow(0).getText()+"_to_"+ctx.struct_multinoun().Struct_noun(index_of_comp_later).getText()+" : out data port;\n";
                                                            first_new_line+=1;
                                                        }
                                                        else {
                                                            new_decl=new_decl+system_declaration[i][0].charAt(j);
                                                        }

                                                    }
                                                    system_declaration[i][0]=new_decl;
                                                }
                                            }
                                        }



                                        // For thr input port of the end comp
//        System.out.println("Start of second end");
                                        already_created_feature=false;
                                        if_present_first_index=-1;
                                        for(int i=0;i<=index_features-1;i++)
                                        {
                                            StringTokenizer st6 = new StringTokenizer(sys_features[i]," ");
                                            String comp_name=st6.nextToken();
                                            String init_struct_name=name_of_comp;
                                            if(comp_name.equals(init_struct_name))
                                            {
                                                already_created_feature=true;
                                                if_present_first_index=i;
                                            }
                                        }
//        System.out.println(ctx.struct_multinoun().getText());
                                        if(already_created_feature)
                                        {
                                            StringTokenizer st6 = new StringTokenizer(sys_features[if_present_first_index]," ");
                                            port_name=st6.nextToken();
                                            valid_port_name="";
                                            has_found_valid_name=false;
//            System.out.println("haha");
                                            while(st6.hasMoreTokens())
                                            {
                                                port_name=st6.nextToken();
                                                if(port_name.equals(ctx.multi_flow(0).getText()+"_from_"+ctx.Struct_noun(0).toString()))
                                                {   has_found_valid_name=true;
                                                    int to_add_at_last=1;
                                                    while(port_name.equals(ctx.multi_flow(0).getText()+"_from_"+ctx.Struct_noun(0).toString()+to_add_at_last))
                                                    {
                                                        to_add_at_last+=1;
                                                    }
                                                    valid_port_name= ctx.multi_flow(0).getText()+"_from_"+ctx.Struct_noun(0).toString()+to_add_at_last;
                                                }
                                            }
//            System.out.println("hehe");
                                            if(has_found_valid_name)
                                            {
                                                sys_features[if_present_first_index]=sys_features[if_present_first_index]+" "+valid_port_name;
                                            }
                                            else {
                                                sys_features[if_present_first_index]=sys_features[if_present_first_index]+" "+ctx.multi_flow(0).getText()+"_from_"+ctx.Struct_noun(0).toString();
                                            }
                                            get_last_port=new StringTokenizer(sys_features[if_present_first_index]," ");
                                            name_of_last_port="";
                                            while(get_last_port.hasMoreTokens())
                                            {
                                                name_of_last_port=get_last_port.nextToken();
                                            }
//            System.out.println(name_of_last_port);
                                            new_decl="";
                                            second_line=1;
                                            for(int i=0;i<=index_names-1;i++) {
                                                if (name_of_comp.equals(system_names[i])) {
//                    System.out.println(ctx.struct_multinoun().getText());
                                                    for (int j = 0; j <= system_declaration[i][0].length() - 1; j++) {
                                                        if (system_declaration[i][0].charAt(j) == '\n' && second_line == 0) {
                                                            new_decl=new_decl+"\n\t\t"+name_of_last_port+" : in data port;\n";
                                                            second_line-=1;
                                                        }
                                                        else {
                                                            new_decl=new_decl+system_declaration[i][0].charAt(j);
                                                            if(system_declaration[i][0].charAt(j) == '\n')
                                                            {
                                                                second_line-=1;
                                                            }
                                                        }

                                                    }
                                                    system_declaration[i][0]=new_decl;
                                                }
                                            }
                                        }
                                        else {
                                            sys_features[index_features] = name_of_comp;
                                            new_decl = "";
                                            sys_features[index_features] = sys_features[index_features] + " " + ctx.multi_flow(0).getText() + "_from_" + ctx.Struct_noun(0).toString();
                                            index_features += 1;
                                            int first_new_line = 0;
                                            for (int i = 0; i <= index_names - 1; i++) {
                                                if (name_of_comp.equals(system_names[i])) {
                                                    for (int j = 0; j <= system_declaration[i][0].length() - 1; j++) {
                                                        if (system_declaration[i][0].charAt(j) == '\n' && first_new_line == 0) {
                                                            new_decl = new_decl + "\n\tfeatures\n";
                                                            new_decl = new_decl + "\t\t" + ctx.multi_flow(0).getText() + "_from_" + ctx.Struct_noun(0).toString() + " : in data port;\n";
                                                            first_new_line += 1;
                                                        } else {
                                                            new_decl = new_decl + system_declaration[i][0].charAt(j);
                                                        }

                                                    }
                                                    system_declaration[i][0] = new_decl;
                                                }
                                            }
                                        }

                                        copy_of_first-=1;
                                        if(copy_of_first==1)
                                        {
                                            break;
                                        }

                                    }
                                }
                            }

                            if(number_of_int_second>=3)
                            {
                                while(copy_of_second>1)
                                {
                                    if(copy_of_second==number_of_int_second)
                                    {
                                        split_second.nextToken();
                                        copy_of_second-=1;
                                        //System.out.println("Skip first");
                                    }
                                    else
                                    {
                                        String name_of_comp=split_second.nextToken();
                                        //System.out.println(name_of_comp);
                                        boolean already_created_feature=false;
                                        int if_present_first_index=-1;
                                        for(int i=0;i<=index_features-1;i++)
                                        {
                                            StringTokenizer st6 = new StringTokenizer(sys_features[i]," ");
                                            String comp_name=st6.nextToken();
                                            String init_struct_name=name_of_comp;
                                            if(comp_name.equals(init_struct_name))
                                            {
                                                already_created_feature=true;
                                                if_present_first_index=i;
                                            }
                                        }

                                        if(already_created_feature)
                                        {
                                            StringTokenizer st6 = new StringTokenizer(sys_features[if_present_first_index]," ");
                                            port_name=st6.nextToken();
                                            valid_port_name="";
                                            has_found_valid_name=false;
                                            while(st6.hasMoreTokens())
                                            {
                                                port_name=st6.nextToken();
                                                if(port_name.equals(ctx.multi_flow(0).getText()+"_to_"+ctx.struct_multinoun().Struct_noun(index_of_comp_later).getText()))
                                                {
                                                    int to_add_at_last=1;
                                                    while(port_name.equals(ctx.multi_flow(0).getText()+"_to_"+ctx.struct_multinoun().Struct_noun(index_of_comp_later).getText()))
                                                    {
                                                        to_add_at_last+=1;
                                                    }
                                                    valid_port_name= ctx.multi_flow(0).getText()+"_to_"+ctx.struct_multinoun().Struct_noun(index_of_comp_later).getText()+to_add_at_last;
                                                    has_found_valid_name=true;
                                                }
                                            }
                                            if(has_found_valid_name)
                                            {
                                                sys_features[if_present_first_index]=sys_features[if_present_first_index]+" "+valid_port_name;
                                            }
                                            else {
                                                sys_features[if_present_first_index]=sys_features[if_present_first_index]+" "+ctx.multi_flow(0).getText()+"_to_"+ctx.struct_multinoun().Struct_noun(index_of_comp_later).getText();
                                            }
                                            get_last_port=new StringTokenizer(sys_features[if_present_first_index]," ");
                                            name_of_last_port="";
                                            while(get_last_port.hasMoreTokens())
                                            {
                                                name_of_last_port=get_last_port.nextToken();
                                            }
                                            new_decl="";
                                            second_line=1;
                                            for(int i=0;i<=index_names-1;i++) {
                                                if (name_of_comp.equals(system_names[i])) {
                                                    for (int j = 0; j <= system_declaration[i][0].length() - 1; j++) {
                                                        if (system_declaration[i][0].charAt(j) == '\n' && second_line == 0) {
                                                            new_decl=new_decl+"\n\t\t"+name_of_last_port+" : out data port;\n";
                                                            second_line-=1;
                                                        }
                                                        else {
                                                            new_decl=new_decl+system_declaration[i][0].charAt(j);
                                                            if(system_declaration[i][0].charAt(j) == '\n')
                                                            {
                                                                second_line-=1;
                                                            }
                                                        }

                                                    }
                                                    system_declaration[i][0]=new_decl;
                                                }
                                            }
                                        }
                                        else {
                                            sys_features[index_features]=name_of_comp;
//            System.out.println(ctx.struct_multinoun().getText());
                                            new_decl="";
                                            sys_features[index_features]=sys_features[index_features]+" "+ctx.multi_flow(0).getText()+"_to_"+ctx.struct_multinoun().Struct_noun(index_of_comp_later).getText();
                                            index_features+=1;
//            System.out.println("Increased");
                                            int first_new_line=0;
                                            for(int i=0;i<=index_names-1;i++) {
                                                if (name_of_comp.equals(system_names[i])) {
                                                    for (int j = 0; j <= system_declaration[i][0].length() - 1; j++) {
                                                        if (system_declaration[i][0].charAt(j) == '\n' && first_new_line == 0) {
                                                            new_decl = new_decl + "\n\tfeatures\n";
                                                            new_decl=new_decl+"\t\t"+ctx.multi_flow(0).getText()+"_to_"+ctx.struct_multinoun().Struct_noun(index_of_comp_later).getText()+" : out data port;\n";
                                                            first_new_line+=1;
                                                        }
                                                        else {
                                                            new_decl=new_decl+system_declaration[i][0].charAt(j);
                                                        }

                                                    }
                                                    system_declaration[i][0]=new_decl;
                                                }
                                            }
                                        }



                                        // For thr input port of the end comp
//        System.out.println("Start of second end");
                                        already_created_feature=false;
                                        if_present_first_index=-1;
                                        for(int i=0;i<=index_features-1;i++)
                                        {
                                            StringTokenizer st6 = new StringTokenizer(sys_features[i]," ");
                                            String comp_name=st6.nextToken();
                                            String init_struct_name=name_of_comp;
                                            if(comp_name.equals(init_struct_name))
                                            {
                                                already_created_feature=true;
                                                if_present_first_index=i;
                                            }
                                        }
//        System.out.println(ctx.struct_multinoun().getText());
                                        if(already_created_feature)
                                        {
                                            StringTokenizer st6 = new StringTokenizer(sys_features[if_present_first_index]," ");
                                            port_name=st6.nextToken();
                                            valid_port_name="";
                                            has_found_valid_name=false;
//            System.out.println("haha");
                                            while(st6.hasMoreTokens())
                                            {
                                                port_name=st6.nextToken();
                                                if(port_name.equals(ctx.multi_flow(0).getText()+"_from_"+ctx.Struct_noun(0).toString()))
                                                {   has_found_valid_name=true;
                                                    int to_add_at_last=1;
                                                    while(port_name.equals(ctx.multi_flow(0).getText()+"_from_"+ctx.Struct_noun(0).toString()+to_add_at_last))
                                                    {
                                                        to_add_at_last+=1;
                                                    }
                                                    valid_port_name= ctx.multi_flow(0).getText()+"_from_"+ctx.Struct_noun(0).toString()+to_add_at_last;
                                                }
                                            }
//            System.out.println("hehe");
                                            if(has_found_valid_name)
                                            {
                                                sys_features[if_present_first_index]=sys_features[if_present_first_index]+" "+valid_port_name;
                                            }
                                            else {
                                                sys_features[if_present_first_index]=sys_features[if_present_first_index]+" "+ctx.multi_flow(0).getText()+"_from_"+ctx.Struct_noun(0).toString();
                                            }
                                            get_last_port=new StringTokenizer(sys_features[if_present_first_index]," ");
                                            name_of_last_port="";
                                            while(get_last_port.hasMoreTokens())
                                            {
                                                name_of_last_port=get_last_port.nextToken();
                                            }
//            System.out.println(name_of_last_port);
                                            new_decl="";
                                            second_line=1;
                                            for(int i=0;i<=index_names-1;i++) {
                                                if (name_of_comp.equals(system_names[i])) {
//                    System.out.println(ctx.struct_multinoun().getText());
                                                    for (int j = 0; j <= system_declaration[i][0].length() - 1; j++) {
                                                        if (system_declaration[i][0].charAt(j) == '\n' && second_line == 0) {
                                                            new_decl=new_decl+"\n\t\t"+name_of_last_port+" : in data port;\n";
                                                            second_line-=1;
                                                        }
                                                        else {
                                                            new_decl=new_decl+system_declaration[i][0].charAt(j);
                                                            if(system_declaration[i][0].charAt(j) == '\n')
                                                            {
                                                                second_line-=1;
                                                            }
                                                        }

                                                    }
                                                    system_declaration[i][0]=new_decl;
                                                }
                                            }
                                        }
                                        else {
                                            sys_features[index_features] = name_of_comp;
                                            new_decl = "";
                                            sys_features[index_features] = sys_features[index_features] + " " + ctx.multi_flow(0).getText() + "_from_" + ctx.Struct_noun(0).toString();
                                            index_features += 1;
                                            int first_new_line = 0;
                                            for (int i = 0; i <= index_names - 1; i++) {
                                                if (name_of_comp.equals(system_names[i])) {
                                                    for (int j = 0; j <= system_declaration[i][0].length() - 1; j++) {
                                                        if (system_declaration[i][0].charAt(j) == '\n' && first_new_line == 0) {
                                                            new_decl = new_decl + "\n\tfeatures\n";
                                                            new_decl = new_decl + "\t\t" + ctx.multi_flow(0).getText() + "_from_" + ctx.Struct_noun(0).toString() + " : in data port;\n";
                                                            first_new_line += 1;
                                                        } else {
                                                            new_decl = new_decl + system_declaration[i][0].charAt(j);
                                                        }

                                                    }
                                                    system_declaration[i][0] = new_decl;
                                                }
                                            }
                                        }

                                        copy_of_second-=1;
                                        if(copy_of_second==1)
                                        {
                                            break;
                                        }

                                    }
                                }

                            }
                            index_of_comp_already_created=-1;
                            already_created=false;
                            for(int i=0;i<=index_features-1;i++)
                            {
                                st=new StringTokenizer(sys_features[i]," ");
                                String comp_name=st.nextToken();
//                        System.out.println(ctx.Struct_noun(0).toString());
//                        System.out.println(comp_name);
                                if(comp_name.equals(ctx.struct_multinoun().Struct_noun(index_of_comp_later).getText()))
                                {
                                    already_created=true;
                                    index_of_comp_already_created=i;
                                    break;
                                }
                            }
//                    System.out.println(already_created);
//                    System.out.println(ctx.getText());
                            if(already_created)
                            {
//                        System.out.println(index_of_comp_later);
//                        System.out.println(index_of_comp_later);
                                st = new StringTokenizer(sys_features[index_of_comp_already_created], " ");
                                port_name = st.nextToken();
                                valid_port_name = "";
                                has_found_valid_name = false;
                                while (st.hasMoreTokens()) {
                                    port_name=st.nextToken();
                                    if (port_name.equals(ctx.multi_flow(0).getText()+"_from_" + ctx.Struct_noun(0).toString())) {
                                        int to_add_at_last = 1;
                                        while (port_name.equals(ctx.multi_flow(0).getText()+"_from_" + ctx.Struct_noun(0).toString() + to_add_at_last)) {
                                            to_add_at_last += 1;
                                        }
                                        valid_port_name = ctx.multi_flow(0).getText()+"_from_" + ctx.Struct_noun(0).toString() + to_add_at_last;
                                        has_found_valid_name = true;
                                    }
                                }
                                if (has_found_valid_name) {
                                    sys_features[index_of_comp_already_created] = sys_features[index_of_comp_already_created] + " " + valid_port_name;
                                }
                                else {
                                    sys_features[index_of_comp_already_created] = sys_features[index_of_comp_already_created] + " " + ctx.multi_flow(0).getText()+"_from_" + ctx.Struct_noun(0).toString();
                                }
                                get_last_port = new StringTokenizer(sys_features[index_of_comp_already_created], " ");
                                name_of_last_port = "";
                                while (get_last_port.hasMoreTokens()) {
                                    name_of_last_port = get_last_port.nextToken();
                                }
//                        System.out.println(name_of_last_port+" : After while");
                                new_decl = "";
                                second_line = 1;
                                for (int i = 0; i <= index_names - 1; i++) {
                                    if (ctx.struct_multinoun().Struct_noun(index_of_comp_later).getText().equals(system_names[i])) {
                                        for (int j = 0; j <= system_declaration[i][0].length() - 1; j++) {
                                            if (system_declaration[i][0].charAt(j) == '\n' && second_line == 0) {
                                                new_decl = new_decl + "\n\t\t" + name_of_last_port + " : in data port;\n";
//                                        System.out.println(name_of_last_port);
                                                second_line -= 1;
                                            } else {
                                                new_decl = new_decl + system_declaration[i][0].charAt(j);
                                                if (system_declaration[i][0].charAt(j) == '\n') {
                                                    second_line -= 1;
                                                }
                                            }

                                        }
                                        system_declaration[i][0] = new_decl;
                                    }
                                }
                            }
                            else {

                                sys_features[index_features]=ctx.struct_multinoun().Struct_noun(index_of_comp_later).getText();
//            System.out.println(ctx.struct_multinoun().getText());
                                new_decl = "";
                                sys_features[index_features] = sys_features[index_features] + " " + ctx.multi_flow(0).getText()+"_from_" + ctx.Struct_noun(0).toString();
                                index_features += 1;
//            System.out.println("Increased");
                                int first_new_line = 0;
                                for (int i = 0; i <= index_names - 1; i++) {
                                    if (ctx.struct_multinoun().Struct_noun(index_of_comp_later).getText().equals(system_names[i])) {
                                        for (int j = 0; j <= system_declaration[i][0].length() - 1; j++) {
                                            if (system_declaration[i][0].charAt(j) == '\n' && first_new_line == 0) {
                                                new_decl = new_decl + "\n\tfeatures\n";
                                                new_decl = new_decl + "\t\t" + ctx.multi_flow(0).getText()+"_from_" + ctx.Struct_noun(0).toString() + " : in data port;\n";
                                                first_new_line += 1;
                                            } else {
                                                new_decl = new_decl + system_declaration[i][0].charAt(j);
                                            }

                                        }
                                        system_declaration[i][0] = new_decl;
                                    }
                                }
                            }

                            if(under_same_component)
                            {
                                int index_of_upper_comp=-1;
                                String upper_comp="";
                                for(int i=0;i<=index_subcomponents-1;i++)
                                {
                                    if(system_subcomponents[i].contains(ctx.Struct_noun(0).toString()) && system_subcomponents[i].contains(ctx.struct_multinoun().Struct_noun(index_of_comp_later).getText()))
                                    {
                                        st=new StringTokenizer(system_subcomponents[i]," ");
                                        upper_comp=st.nextToken();
                                    }
                                }
                                for(int i=0; i<=index_names-1;i++)
                                {
                                    if(system_names[i].equals(upper_comp))
                                    {
                                        index_of_upper_comp=i;
                                    }
                                }
                                int count_of_connections=0;
                                boolean already_connections=false;
                                for(int i=0;i<=index_connections-1;i++)
                                {
                                    if(connections[i].equals(upper_comp))
                                    {
                                        already_connections=true;
                                        count_of_connections=number_of_connections[i];
                                    }
                                }
                                new_decl="";
                                int nextline_char=0;
                                String port_name_of_first="";
                                String port_name_of_second="";

                                for(int i=0;i<=index_features-1;i++)
                                {
                                    st= new StringTokenizer(sys_features[i]," ");
                                    String name_of_comp_having_features=st.nextToken();
                                    if(ctx.Struct_noun(0).toString().equals(name_of_comp_having_features))
                                    {
                                        while(st.hasMoreTokens())
                                        {
                                            port_name_of_first=st.nextToken();
                                        }
                                    }
                                    else if(ctx.struct_multinoun().Struct_noun(index_of_comp_later).getText().equals(name_of_comp_having_features))
                                    {
                                        while(st.hasMoreTokens())
                                        {
                                            port_name_of_second=st.nextToken();
                                        }
                                    }
                                    else {

                                    }
                                }

                                for(int i=system_declaration[index_of_upper_comp][1].length()-1; i>=0; i--)
                                {
                                    if(system_declaration[index_of_upper_comp][1].charAt(i)=='\n' && nextline_char==1)
                                    {
                                        if(already_connections)
                                        {
                                            for(int k=0;k<=index_connections-1;k++)
                                            {
                                                if(upper_comp.equals(connections[k]))
                                                {
                                                    number_of_connections[k]+=1;
                                                    new_decl="\n\t\t"+upper_comp+count_of_connections+": port "+"this_"+ctx.Struct_noun(0).toString()+"."+port_name_of_first+"->this_"+ctx.struct_multinoun().Struct_noun(index_of_comp_later).getText()+"."+port_name_of_second+";\n" + new_decl;
                                                    update_list_flows(upper_comp, ctx.Struct_noun(0).toString(), port_name_of_first ,ctx.struct_multinoun().Struct_noun(index_of_comp_later).getText(), port_name_of_second);
                                                    break;
                                                }
                                            }
                                        }
                                        else {
                                            connections[index_connections]=upper_comp;
                                            index_connections+=1;
                                            number_of_connections[index_connections-1]=1;
                                            new_decl="\n\tconnections\n"+"\t\t"+upper_comp+"0"+": port "+"this_"+ctx.Struct_noun(0).toString()+"."+port_name_of_first+"->this_"+ctx.struct_multinoun().Struct_noun(index_of_comp_later).getText()+"."+port_name_of_second+";\n" + new_decl;
                                            update_list_flows(upper_comp, ctx.Struct_noun(0).toString(), port_name_of_first ,ctx.struct_multinoun().Struct_noun(index_of_comp_later).getText(), port_name_of_second);
                                            }
                                        nextline_char+=1;
                                    }
                                    else
                                    {
                                        if(system_declaration[index_of_upper_comp][1].charAt(i)=='\n')
                                        {
                                            nextline_char+=1;
                                        }
                                        new_decl=system_declaration[index_of_upper_comp][1].charAt(i)+new_decl;
                                    }
                                }
                                system_declaration[index_of_upper_comp][1]=new_decl;
                            }
                            else {

                                //System.out.println(path_to_first);
                                //System.out.println(path_to_second);


                                number_of_int_first = 0;
                                number_of_int_second = 0;

                                split_first = new StringTokenizer(path_to_first, "-");
                                split_second = new StringTokenizer(path_to_second, "-");

                                while (split_first.hasMoreTokens()) {
                                    number_of_int_first += 1;
                                    split_first.nextToken();
                                }

                                while (split_second.hasMoreTokens()) {
                                    number_of_int_second += 1;
                                    split_second.nextToken();
                                }

                                //For intermediate systems within the path of first and second
                                if (number_of_int_first >= 3) {
                                    String ind_sys_first[] = new String[number_of_int_first - 1];
                                    int index_of_ind_sys_first = 0;
                                    split_first = new StringTokenizer(path_to_first, "-");
                                    split_first.nextToken();
                                    while (split_first.hasMoreTokens()) {
                                        ind_sys_first[index_of_ind_sys_first] = split_first.nextToken();
                                        index_of_ind_sys_first += 1;
                                    }
                                    String upper_comp = "";
                                    for (int k = 0; k <= index_of_ind_sys_first - 2; k++) {
                                        for (int l = 0; l <= index_subcomponents - 1; l++) {
                                            if (system_subcomponents[l].contains(ind_sys_first[k]) && system_subcomponents[l].contains(ind_sys_first[k + 1])) {
                                                st = new StringTokenizer(system_subcomponents[l], " ");
                                                upper_comp = st.nextToken();
                                            }
                                        }

                                        int index_of_upper_comp = -1;
                                        for (int i = 0; i <= index_names - 1; i++) {
                                            if (system_names[i].equals(upper_comp)) {
                                                index_of_upper_comp = i;
                                            }
                                        }
                                        int count_of_connections = 0;
                                        boolean already_connections = false;
                                        for (int i = 0; i <= index_connections - 1; i++) {
                                            if (connections[i].equals(upper_comp)) {
                                                already_connections = true;
                                                count_of_connections = number_of_connections[i];
                                            }
                                        }
                                        new_decl = "";
                                        int nextline_char = 0;
                                        String port_name_of_first = "";
                                        String port_name_of_second = "";

                                        for (int i = 0; i <= index_features - 1; i++) {
                                            st = new StringTokenizer(sys_features[i], " ");
                                            String name_of_comp_having_features = st.nextToken();
                                            String second_last_comp = "";
                                            if (ind_sys_first[k].equals(name_of_comp_having_features)) {
                                                while (st.hasMoreTokens()) {
                                                    port_name_of_first = st.nextToken();
                                                }
                                            } else if (ind_sys_first[k + 1].equals(name_of_comp_having_features)) {
                                                while (st.hasMoreTokens()) {
                                                    second_last_comp = port_name_of_second;
                                                    port_name_of_second = st.nextToken();
                                                }
                                                if(k==index_of_ind_sys_first-2)
                                                {

                                                }
                                                else {
                                                    port_name_of_second=second_last_comp;
                                                }
                                            } else {

                                            }
                                        }

                                        for (int i = system_declaration[index_of_upper_comp][1].length() - 1; i >= 0; i--) {
                                            if (system_declaration[index_of_upper_comp][1].charAt(i) == '\n' && nextline_char == 1) {
                                                if (already_connections) {
                                                    for (int m = 0; m <= index_connections - 1; m++) {
                                                        if (upper_comp.equals(connections[m])) {
                                                            number_of_connections[m] += 1;
                                                            new_decl = "\n\t\t" + upper_comp + count_of_connections + ": port " + "this_" + ind_sys_first[k+1] + "." + port_name_of_second + "->this_" + ind_sys_first[k] + "." + port_name_of_first + ";\n" + new_decl;
                                                            update_list_flows(upper_comp, ind_sys_first[k], port_name_of_first ,ind_sys_first[k+1], port_name_of_second);
                                                            break;
                                                        }
                                                    }
                                                } else {
                                                    connections[index_connections] = upper_comp;
                                                    index_connections += 1;
                                                    number_of_connections[index_connections - 1] = 1;
                                                    new_decl = "\n\tconnections\n" + "\t\t" + upper_comp + "0" + ": port " + "this_" + ind_sys_first[k+1] + "." + port_name_of_second + "->this_" + ind_sys_first[k] + "." + port_name_of_first + ";\n" + new_decl;
                                                    update_list_flows(upper_comp, ind_sys_first[k], port_name_of_first ,ind_sys_first[k+1], port_name_of_second);
                                                }
                                                nextline_char += 1;
                                            } else {
                                                if (system_declaration[index_of_upper_comp][1].charAt(i) == '\n') {
                                                    nextline_char += 1;
                                                }
                                                new_decl = system_declaration[index_of_upper_comp][1].charAt(i) + new_decl;
                                            }
                                        }
                                        system_declaration[index_of_upper_comp][1] = new_decl;
//                    System.out.println(system_declaration[index_of_upper_comp][1]);

                                    }
                                }
                                if (number_of_int_second >= 3) {
                                    String ind_sys_second[] = new String[number_of_int_second - 1];
                                    int index_of_ind_sys_second = 0;
                                    split_second = new StringTokenizer(path_to_second, "-");
                                    split_second.nextToken();
                                    while (split_second.hasMoreTokens()) {
                                        ind_sys_second[index_of_ind_sys_second] = split_second.nextToken();
                                        index_of_ind_sys_second += 1;
                                    }

                                    String upper_comp = "";
                                    for (int k = 0; k <= index_of_ind_sys_second - 2; k++) {
                                        for (int l = 0; l <= index_subcomponents - 1; l++) {
                                            if (system_subcomponents[l].contains(ind_sys_second[k]) && system_subcomponents[l].contains(ind_sys_second[k + 1])) {
                                                st = new StringTokenizer(system_subcomponents[l], " ");
                                                upper_comp = st.nextToken();
                                            }
                                        }

                                        int index_of_upper_comp = -1;
                                        for (int i = 0; i <= index_names - 1; i++) {
                                            if (system_names[i].equals(upper_comp)) {
                                                index_of_upper_comp = i;
                                            }
                                        }
                                        int count_of_connections = 0;
                                        boolean already_connections = false;
                                        for (int i = 0; i <= index_connections - 1; i++) {
                                            if (connections[i].equals(upper_comp)) {
                                                already_connections = true;
                                                count_of_connections = number_of_connections[i];
                                            }
                                        }
                                        new_decl = "";
                                        int nextline_char = 0;
                                        String port_name_of_first = "";
                                        String port_name_of_second = "";

                                        for (int i = 0; i <= index_features - 1; i++) {
                                            st = new StringTokenizer(sys_features[i], " ");
                                            String name_of_comp_having_features = st.nextToken();
                                            String second_last_port="";
                                            if (ind_sys_second[k].equals(name_of_comp_having_features)) {
                                                while (st.hasMoreTokens()) {
                                                    second_last_port=port_name_of_first;
                                                    port_name_of_first = st.nextToken();
                                                }
                                                port_name_of_first=second_last_port;
                                            } else if (ind_sys_second[k + 1].equals(name_of_comp_having_features)) {
                                                while (st.hasMoreTokens()) {
                                                    port_name_of_second = st.nextToken();
                                                }
                                            } else {

                                            }
                                        }

                                        for (int i = system_declaration[index_of_upper_comp][1].length() - 1; i >= 0; i--) {
                                            if (system_declaration[index_of_upper_comp][1].charAt(i) == '\n' && nextline_char == 1) {
                                                if (already_connections) {
                                                    for (int m = 0; m <= index_connections - 1; m++) {
                                                        if (upper_comp.equals(connections[m])) {
                                                            number_of_connections[m] += 1;
                                                            new_decl = "\n\t\t" + upper_comp + count_of_connections + ": port " + "this_" + ind_sys_second[k] + "." + port_name_of_first + "->this_" + ind_sys_second[k + 1] + "." + port_name_of_second + ";\n" + new_decl;
                                                            update_list_flows(upper_comp, ind_sys_second[k], port_name_of_first ,ind_sys_second[k+1], port_name_of_second);
                                                            break;
                                                        }
                                                    }
                                                } else {
                                                    connections[index_connections] = upper_comp;
                                                    index_connections += 1;
                                                    number_of_connections[index_connections - 1] = 1;
                                                    new_decl = "\n\tconnections\n" + "\t\t" + upper_comp + "0" + ": port " + "this_" + ind_sys_second[k] + "." + port_name_of_first + "->this_" + ind_sys_second[k + 1] + "." + port_name_of_second + ";\n" + new_decl;
                                                    update_list_flows(upper_comp, ind_sys_second[k], port_name_of_first ,ind_sys_second[k+1], port_name_of_second);
                                                }
                                                nextline_char += 1;
                                            } else {
                                                if (system_declaration[index_of_upper_comp][1].charAt(i) == '\n') {
                                                    nextline_char += 1;
                                                }
                                                new_decl = system_declaration[index_of_upper_comp][1].charAt(i) + new_decl;
                                            }
                                        }
                                        system_declaration[index_of_upper_comp][1] = new_decl;
                                        //System.out.println(system_declaration[index_of_upper_comp][1]);

                                    }
                                }


                                //For connections between first of p1 and first of p2
                                String comm_conn_first = "";
                                String comm_conn_second = "";
                                split_first = new StringTokenizer(path_to_first, "-");
                                split_second = new StringTokenizer(path_to_second, "-");
                                split_first.nextToken();
                                comm_conn_first = split_first.nextToken();
                                split_second.nextToken();
                                comm_conn_second = split_second.nextToken();

                                int index_of_upper_comp=-1;
                                String upper_comp="";
                                for(int i=0;i<=index_subcomponents-1;i++)
                                {
                                    if(system_subcomponents[i].contains(comm_conn_first) && system_subcomponents[i].contains(comm_conn_second))
                                    {
                                        st=new StringTokenizer(system_subcomponents[i]," ");
                                        upper_comp=st.nextToken();
                                    }
                                }
                                for(int i=0; i<=index_names-1;i++)
                                {
                                    if(system_names[i].equals(upper_comp))
                                    {
                                        index_of_upper_comp=i;
                                    }
                                }
                                int count_of_connections=0;
                                boolean already_connections=false;
                                for(int i=0;i<=index_connections-1;i++)
                                {
                                    if(connections[i].equals(upper_comp))
                                    {
                                        already_connections=true;
                                        count_of_connections=number_of_connections[i];
                                    }
                                }
                                new_decl="";
                                int nextline_char=0;
                                String port_name_of_first="";
                                String port_name_of_second="";

                                for(int i=0;i<=index_features-1;i++)
                                {
                                    st= new StringTokenizer(sys_features[i]," ");
                                    String name_of_comp_having_features=st.nextToken();
                                    String second_last_conn="";
                                    if(comm_conn_first.equals(name_of_comp_having_features))
                                    {
                                        while(st.hasMoreTokens())
                                        {
                                            second_last_conn=port_name_of_first;
                                            port_name_of_first=st.nextToken();
                                        }
                                        if(number_of_int_first>=3)
                                        {
                                            port_name_of_first=second_last_conn;
                                        }
                                    }
                                    else if(comm_conn_second.equals(name_of_comp_having_features))
                                    {
                                        while(st.hasMoreTokens())
                                        {
                                            port_name_of_second=st.nextToken();
                                        }
                                    }
                                    else {

                                    }
                                }


                                for(int i=system_declaration[index_of_upper_comp][1].length()-1; i>=0; i--)
                                {
                                    if(system_declaration[index_of_upper_comp][1].charAt(i)=='\n' && nextline_char==1)
                                    {
                                        if(already_connections)
                                        {
                                            for(int k=0;k<=index_connections-1;k++)
                                            {
                                                if(upper_comp.equals(connections[k]))
                                                {
                                                    number_of_connections[k]+=1;
                                                    new_decl="\n\t\t"+upper_comp+count_of_connections+": port "+"this_"+comm_conn_first+"."+port_name_of_first+"->this_"+comm_conn_second+"."+port_name_of_second+";\n" + new_decl;
                                                    update_list_flows(upper_comp, comm_conn_first, port_name_of_first ,comm_conn_second, port_name_of_second);
                                                    break;
                                                }
                                            }
                                        }
                                        else {
                                            connections[index_connections]=upper_comp;
                                            index_connections+=1;
                                            number_of_connections[index_connections-1]=1;
                                            new_decl="\n\tconnections\n"+"\t\t"+upper_comp+"0"+": port "+"this_"+comm_conn_first+"."+port_name_of_first+"->this_"+comm_conn_second+"."+port_name_of_second+";\n" + new_decl;
                                            update_list_flows(upper_comp, comm_conn_first, port_name_of_first ,comm_conn_second, port_name_of_second);
                                        }
                                        nextline_char+=1;
                                    }
                                    else
                                    {
                                        if(system_declaration[index_of_upper_comp][1].charAt(i)=='\n')
                                        {
                                            nextline_char+=1;
                                        }
                                        new_decl=system_declaration[index_of_upper_comp][1].charAt(i)+new_decl;
                                    }
                                }
                                system_declaration[index_of_upper_comp][1]=new_decl;

                            }
                        index_of_comp_later+=1;
                        }
                    }

                    //Enter here also
                    else {

                        sys_features[index_features]=ctx.Struct_noun(0).toString();
//            System.out.println(ctx.struct_multinoun().getText());
                        while(index_of_comp_later<=1) {

                            String path_to_first=ctx.Struct_noun(0).toString();
                            String path_to_second="";
                            boolean under_same_component=false;
                            for(int i=0;i<=index_subcomponents-1;i++)
                            {
                                if(system_subcomponents[i].contains(ctx.Struct_noun(0).toString()) && system_subcomponents[i].contains(ctx.struct_multinoun().Struct_noun(index_of_comp_later).getText()))
                                {
                                    under_same_component=true;
                                    path_to_second=ctx.struct_multinoun().Struct_noun(index_of_comp_later).getText();
//                                   //System.out.println("Under same component");
                                }

                            }
                            if(!under_same_component) {
                                int index_of_first_comp_upper = -1;
                                boolean found_it = false;
                                int index_of_second_comp_upper = -1;
                                for (int i = 0; i <= index_subcomponents - 1; i++) {
                                    if (system_subcomponents[i].contains(ctx.struct_multinoun().Struct_noun(index_of_comp_later).getText())) {
                                        index_of_second_comp_upper = i;
                                    }
                                    if (system_subcomponents[i].contains(ctx.Struct_noun(0).toString())) {
                                        index_of_first_comp_upper = i;
                                    }
                                }

                                int copy_of_second_comp = index_of_second_comp_upper;
                                String second = ctx.struct_multinoun().Struct_noun(index_of_comp_later).getText();
                                while (index_of_first_comp_upper >= 0) {
                                    StringTokenizer st = new StringTokenizer(system_subcomponents[index_of_first_comp_upper], " ");
                                    String first = st.nextToken();
                                    if(first.equals(path_to_first))
                                    {

                                    }
                                    else {
                                        path_to_first = first + "-" + path_to_first;
                                    }
                                    second = ctx.struct_multinoun().Struct_noun(index_of_comp_later).getText();
                                    path_to_second = second;
                                    while (index_of_second_comp_upper >= 0) {
                                        for (int i = 0; i <= index_subcomponents - 1; i++) {
                                            if (system_subcomponents[i].contains(first) && system_subcomponents[i].contains(second)) {
                                                StringTokenizer st3 = new StringTokenizer(system_subcomponents[i], " ");
                                                String common_comp_for_conn = st3.nextToken();
                                                path_to_first = common_comp_for_conn + "-" + path_to_first;
                                                path_to_second = common_comp_for_conn + "-" + path_to_second;
                                                found_it = true;
                                                break;
                                            }
                                        }
                                        if (found_it) {
                                            break;
                                        } else {
                                            if (index_of_second_comp_upper == 0) {
                                                break;
                                            }
                                            for (int j = 0; j <= index_of_second_comp_upper; j++) {
                                                boolean val=false;
                                                if (system_subcomponents[j].contains(second)) {
                                                    //System.out.println("Outer second");
                                                    index_of_second_comp_upper = j;
                                                    StringTokenizer st2 = new StringTokenizer(system_subcomponents[index_of_second_comp_upper], " ");
                                                    String new_second = st2.nextToken();
                                                    if(new_second.equals(second))
                                                    {
                                                        for (int k = 0; k <= index_of_second_comp_upper-1; k++) {
                                                            //System.out.println("Inner second");
                                                            if (system_subcomponents[k].contains(second)) {
                                                                index_of_second_comp_upper = k;
                                                                st2 = new StringTokenizer(system_subcomponents[index_of_second_comp_upper], " ");
                                                                second = st2.nextToken();
                                                                path_to_second = second + "-" + path_to_second;
                                                                val=true;
                                                                break;
                                                            }
                                                        }
                                                    }
                                                    else {
                                                        second=new_second;
                                                        path_to_second = second + "-" + path_to_second;
                                                        val=true;
                                                        break;
                                                    }
                                                    if(val)
                                                    {
                                                        break;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    if (found_it) {
                                        break;
                                    } else {
                                        if (index_of_first_comp_upper == 0) {
                                            break;
                                        }
                                        for (int j = 0; j <= index_of_first_comp_upper; j++) {
                                            //System.out.println("Outer first");
                                            boolean val=false;
                                            if (system_subcomponents[j].contains(first)) {
                                                index_of_first_comp_upper = j;
                                                StringTokenizer st2 = new StringTokenizer(system_subcomponents[index_of_first_comp_upper], " ");
                                                String new_first = st2.nextToken();
                                                if(first.equals(new_first)) {
                                                    for (int k = 0; k <= index_of_first_comp_upper-1; k++) {
                                                        //System.out.println("Inner first");
                                                        if (system_subcomponents[k].contains(first)) {
                                                            index_of_first_comp_upper = k;
                                                            val=true;
                                                            break;
                                                        }
                                                    }
                                                }
                                                else{
                                                    val=true;
                                                    break;
                                                }
                                                if(val)
                                                {
                                                    break;
                                                }
                                            }
                                        }
                                    }
                                }

                            }
                            String new_decl = "";
                            sys_features[index_features] = sys_features[index_features] + " " + ctx.multi_flow(0).getText()+"_to_" + ctx.struct_multinoun().Struct_noun(index_of_comp_later).getText();
                            index_features += 1;
//            System.out.println("Increased");
                            int first_new_line = 0;
                            for (int i = 0; i <= index_names - 1; i++) {
                                if (ctx.Struct_noun(0).toString().equals(system_names[i])) {
                                    for (int j = 0; j <= system_declaration[i][0].length() - 1; j++) {
                                        if (system_declaration[i][0].charAt(j) == '\n' && first_new_line == 0) {
                                            new_decl = new_decl + "\n\tfeatures\n";
                                            new_decl = new_decl + "\t\t" + ctx.multi_flow(0).getText()+"_to_" + ctx.struct_multinoun().Struct_noun(index_of_comp_later).getText() + " : out data port;\n";
                                            first_new_line += 1;
                                        } else {
                                            new_decl = new_decl + system_declaration[i][0].charAt(j);
                                        }

                                    }
                                    system_declaration[i][0] = new_decl;
                                }
                            }
                            StringTokenizer split_first= new StringTokenizer(path_to_first,"-");
                            int number_of_int_first=0;
                            while(split_first.hasMoreTokens())
                            {
                                String inter_sys=split_first.nextToken();
                                number_of_int_first+=1;
                            }

                            StringTokenizer split_second= new StringTokenizer(path_to_second,"-");
                            int number_of_int_second=0;
                            while(split_second.hasMoreTokens())
                            {
                                String inter_sys=split_second.nextToken();
                                number_of_int_second+=1;
                            }

                            int copy_of_first=number_of_int_first;
                            int copy_of_second=number_of_int_second;

                            split_first= new StringTokenizer(path_to_first,"-");
                            split_second=new StringTokenizer(path_to_second,"-");

                            //System.out.println(path_to_first);
                            //System.out.println(path_to_second);
                            //System.out.println(number_of_int_first);
                            //System.out.println(number_of_int_second);

                            if(number_of_int_first>=3)
                            {
                                while(copy_of_first>1)
                                {
                                    if(copy_of_first==number_of_int_first)
                                    {
                                        split_first.nextToken();
                                        copy_of_first-=1;
                                    }
                                    else
                                    {
                                        String name_of_comp=split_first.nextToken();
                                        boolean already_created_feature=false;
                                        int if_present_first_index=-1;
                                        for(int i=0;i<=index_features-1;i++)
                                        {
                                            StringTokenizer st6 = new StringTokenizer(sys_features[i]," ");
                                            String comp_name=st6.nextToken();
                                            String init_struct_name=name_of_comp;
                                            if(comp_name.equals(init_struct_name))
                                            {
                                                already_created_feature=true;
                                                if_present_first_index=i;
                                            }
                                        }

                                        if(already_created_feature)
                                        {
                                            StringTokenizer st6 = new StringTokenizer(sys_features[if_present_first_index]," ");
                                            String port_name=st6.nextToken();
                                            String valid_port_name="";
                                            boolean has_found_valid_name=false;
                                            while(st6.hasMoreTokens())
                                            {
                                                port_name=st6.nextToken();
                                                if(port_name.equals(ctx.multi_flow(0).getText()+"_to_"+ctx.struct_multinoun().Struct_noun(index_of_comp_later).getText()))
                                                {
                                                    int to_add_at_last=1;
                                                    while(port_name.equals(ctx.multi_flow(0).getText()+"_to_"+ctx.struct_multinoun().Struct_noun(index_of_comp_later).getText()+to_add_at_last))
                                                    {
                                                        to_add_at_last+=1;
                                                    }
                                                    valid_port_name= ctx.multi_flow(0).getText()+"_to_"+ctx.struct_multinoun().Struct_noun(index_of_comp_later).getText()+to_add_at_last;
                                                    has_found_valid_name=true;
                                                }
                                            }
                                            if(has_found_valid_name)
                                            {
                                                sys_features[if_present_first_index]=sys_features[if_present_first_index]+" "+valid_port_name;
                                            }
                                            else {
                                                sys_features[if_present_first_index]=sys_features[if_present_first_index]+" "+ctx.multi_flow(0).getText()+"_to_"+ctx.struct_multinoun().Struct_noun(index_of_comp_later).getText();
                                            }
                                            StringTokenizer get_last_port=new StringTokenizer(sys_features[if_present_first_index]," ");
                                            String name_of_last_port="";
                                            while(get_last_port.hasMoreTokens())
                                            {
                                                name_of_last_port=get_last_port.nextToken();
                                            }
                                            new_decl="";
                                            int second_line=1;
                                            for(int i=0;i<=index_names-1;i++) {
                                                if (name_of_comp.equals(system_names[i])) {
                                                    for (int j = 0; j <= system_declaration[i][0].length() - 1; j++) {
                                                        if (system_declaration[i][0].charAt(j) == '\n' && second_line == 0) {
                                                            new_decl=new_decl+"\n\t\t"+name_of_last_port+" : out data port;\n";
                                                            second_line-=1;
                                                        }
                                                        else {
                                                            new_decl=new_decl+system_declaration[i][0].charAt(j);
                                                            if(system_declaration[i][0].charAt(j) == '\n')
                                                            {
                                                                second_line-=1;
                                                            }
                                                        }

                                                    }
                                                    system_declaration[i][0]=new_decl;
                                                }
                                            }
                                        }
                                        else {
                                            sys_features[index_features]=name_of_comp;
//            System.out.println(ctx.struct_multinoun().getText());
                                            new_decl="";
                                            sys_features[index_features]=sys_features[index_features]+" "+ctx.multi_flow(0).getText()+"_to_"+ctx.struct_multinoun().Struct_noun(index_of_comp_later).getText();
                                            index_features+=1;
//            System.out.println("Increased");
                                            first_new_line=0;
                                            for(int i=0;i<=index_names-1;i++) {
                                                if (name_of_comp.equals(system_names[i])) {
                                                    for (int j = 0; j <= system_declaration[i][0].length() - 1; j++) {
                                                        if (system_declaration[i][0].charAt(j) == '\n' && first_new_line == 0) {
                                                            new_decl = new_decl + "\n\tfeatures\n";
                                                            new_decl=new_decl+"\t\t"+ctx.multi_flow(0).getText()+"_to_"+ctx.struct_multinoun().Struct_noun(index_of_comp_later).getText()+" : out data port;\n";
                                                            first_new_line+=1;
                                                        }
                                                        else {
                                                            new_decl=new_decl+system_declaration[i][0].charAt(j);
                                                        }

                                                    }
                                                    system_declaration[i][0]=new_decl;
                                                }
                                            }
                                        }



                                        // For thr input port of the end comp
//        System.out.println("Start of second end");
                                        already_created_feature=false;
                                        if_present_first_index=-1;
                                        for(int i=0;i<=index_features-1;i++)
                                        {
                                            StringTokenizer st6 = new StringTokenizer(sys_features[i]," ");
                                            String comp_name=st6.nextToken();
                                            String init_struct_name=name_of_comp;
                                            if(comp_name.equals(init_struct_name))
                                            {
                                                already_created_feature=true;
                                                if_present_first_index=i;
                                            }
                                        }
//        System.out.println(ctx.struct_multinoun().getText());
                                        if(already_created_feature)
                                        {
                                            StringTokenizer st6 = new StringTokenizer(sys_features[if_present_first_index]," ");
                                            String port_name=st6.nextToken();
                                            String valid_port_name="";
                                            boolean has_found_valid_name=false;
//            System.out.println("haha");
                                            while(st6.hasMoreTokens())
                                            {
                                                port_name=st6.nextToken();
                                                if(port_name.equals(ctx.multi_flow(0).getText()+"_from_"+ctx.Struct_noun(0).toString()))
                                                {   has_found_valid_name=true;
                                                    int to_add_at_last=1;
                                                    while(port_name.equals(ctx.multi_flow(0).getText()+"_from_"+ctx.Struct_noun(0).toString()+to_add_at_last))
                                                    {
                                                        to_add_at_last+=1;
                                                    }
                                                    valid_port_name= ctx.multi_flow(0).getText()+"_from_"+ctx.Struct_noun(0).toString()+to_add_at_last;
                                                }
                                            }
//            System.out.println("hehe");
                                            if(has_found_valid_name)
                                            {
                                                sys_features[if_present_first_index]=sys_features[if_present_first_index]+" "+valid_port_name;
                                            }
                                            else {
                                                sys_features[if_present_first_index]=sys_features[if_present_first_index]+" "+ctx.multi_flow(0).getText()+"_from_"+ctx.Struct_noun(0).toString();
                                            }
                                            StringTokenizer get_last_port=new StringTokenizer(sys_features[if_present_first_index]," ");
                                            String name_of_last_port="";
                                            while(get_last_port.hasMoreTokens())
                                            {
                                                name_of_last_port=get_last_port.nextToken();
                                            }
//            System.out.println(name_of_last_port);
                                            new_decl="";
                                            int second_line=1;
                                            for(int i=0;i<=index_names-1;i++) {
                                                if (name_of_comp.equals(system_names[i])) {
//                    System.out.println(ctx.struct_multinoun().getText());
                                                    for (int j = 0; j <= system_declaration[i][0].length() - 1; j++) {
                                                        if (system_declaration[i][0].charAt(j) == '\n' && second_line == 0) {
                                                            new_decl=new_decl+"\n\t\t"+name_of_last_port+" : in data port;\n";
                                                            second_line-=1;
                                                        }
                                                        else {
                                                            new_decl=new_decl+system_declaration[i][0].charAt(j);
                                                            if(system_declaration[i][0].charAt(j) == '\n')
                                                            {
                                                                second_line-=1;
                                                            }
                                                        }

                                                    }
                                                    system_declaration[i][0]=new_decl;
                                                }
                                            }
                                        }
                                        else {
                                            sys_features[index_features] = name_of_comp;
                                            new_decl = "";
                                            sys_features[index_features] = sys_features[index_features] + " " + ctx.multi_flow(0).getText() + "_from_" + ctx.Struct_noun(0).toString();
                                            index_features += 1;
                                            first_new_line = 0;
                                            for (int i = 0; i <= index_names - 1; i++) {
                                                if (name_of_comp.equals(system_names[i])) {
                                                    for (int j = 0; j <= system_declaration[i][0].length() - 1; j++) {
                                                        if (system_declaration[i][0].charAt(j) == '\n' && first_new_line == 0) {
                                                            new_decl = new_decl + "\n\tfeatures\n";
                                                            new_decl = new_decl + "\t\t" + ctx.multi_flow(0).getText() + "_from_" + ctx.Struct_noun(0).toString() + " : in data port;\n";
                                                            first_new_line += 1;
                                                        } else {
                                                            new_decl = new_decl + system_declaration[i][0].charAt(j);
                                                        }

                                                    }
                                                    system_declaration[i][0] = new_decl;
                                                }
                                            }
                                        }

                                        copy_of_first-=1;
                                        if(copy_of_first==1)
                                        {
                                            break;
                                        }

                                    }
                                }
                            }

                            if(number_of_int_second>=3)
                            {
                                while(copy_of_second>1)
                                {
                                    if(copy_of_second==number_of_int_second)
                                    {
                                        split_second.nextToken();
                                        copy_of_second-=1;
                                        //System.out.println("Skip first");
                                    }
                                    else
                                    {
                                        String name_of_comp=split_second.nextToken();
                                        //System.out.println(name_of_comp);
                                        boolean already_created_feature=false;
                                        int if_present_first_index=-1;
                                        for(int i=0;i<=index_features-1;i++)
                                        {
                                            StringTokenizer st6 = new StringTokenizer(sys_features[i]," ");
                                            String comp_name=st6.nextToken();
                                            String init_struct_name=name_of_comp;
                                            if(comp_name.equals(init_struct_name))
                                            {
                                                already_created_feature=true;
                                                if_present_first_index=i;
                                            }
                                        }

                                        if(already_created_feature)
                                        {
                                            StringTokenizer st6 = new StringTokenizer(sys_features[if_present_first_index]," ");
                                            String port_name=st6.nextToken();
                                            String valid_port_name="";
                                            boolean has_found_valid_name=false;
                                            while(st6.hasMoreTokens())
                                            {
                                                if(port_name.equals(ctx.multi_flow(0).getText()+"_to_"+ctx.struct_multinoun().Struct_noun(index_of_comp_later).getText()))
                                                {
                                                    port_name=st6.nextToken();
                                                    int to_add_at_last=1;
                                                    while(port_name.equals(ctx.multi_flow(0).getText()+"_to_"+ctx.struct_multinoun().Struct_noun(index_of_comp_later).getText()))
                                                    {
                                                        to_add_at_last+=1;
                                                    }
                                                    valid_port_name= ctx.multi_flow(0).getText()+"_to_"+ctx.struct_multinoun().Struct_noun(index_of_comp_later).getText()+to_add_at_last;
                                                    has_found_valid_name=true;
                                                }
                                            }
                                            if(has_found_valid_name)
                                            {
                                                sys_features[if_present_first_index]=sys_features[if_present_first_index]+" "+valid_port_name;
                                            }
                                            else {
                                                sys_features[if_present_first_index]=sys_features[if_present_first_index]+" "+ctx.multi_flow(0).getText()+"_to_"+ctx.struct_multinoun().Struct_noun(index_of_comp_later).getText();
                                            }
                                            StringTokenizer get_last_port=new StringTokenizer(sys_features[if_present_first_index]," ");
                                            String name_of_last_port="";
                                            while(get_last_port.hasMoreTokens())
                                            {
                                                name_of_last_port=get_last_port.nextToken();
                                            }
                                            new_decl="";
                                            int second_line=1;
                                            for(int i=0;i<=index_names-1;i++) {
                                                if (name_of_comp.equals(system_names[i])) {
                                                    for (int j = 0; j <= system_declaration[i][0].length() - 1; j++) {
                                                        if (system_declaration[i][0].charAt(j) == '\n' && second_line == 0) {
                                                            new_decl=new_decl+"\n\t\t"+name_of_last_port+" : out data port;\n";
                                                            second_line-=1;
                                                        }
                                                        else {
                                                            new_decl=new_decl+system_declaration[i][0].charAt(j);
                                                            if(system_declaration[i][0].charAt(j) == '\n')
                                                            {
                                                                second_line-=1;
                                                            }
                                                        }

                                                    }
                                                    system_declaration[i][0]=new_decl;
                                                }
                                            }
                                        }
                                        else {
                                            sys_features[index_features]=name_of_comp;
//            System.out.println(ctx.struct_multinoun().getText());
                                            new_decl="";
                                            sys_features[index_features]=sys_features[index_features]+" "+ctx.multi_flow(0).getText()+"_to_"+ctx.struct_multinoun().Struct_noun(index_of_comp_later).getText();
                                            index_features+=1;
//            System.out.println("Increased");
                                            first_new_line=0;
                                            for(int i=0;i<=index_names-1;i++) {
                                                if (name_of_comp.equals(system_names[i])) {
                                                    for (int j = 0; j <= system_declaration[i][0].length() - 1; j++) {
                                                        if (system_declaration[i][0].charAt(j) == '\n' && first_new_line == 0) {
                                                            new_decl = new_decl + "\n\tfeatures\n";
                                                            new_decl=new_decl+"\t\t"+ctx.multi_flow(0).getText()+"_to_"+ctx.struct_multinoun().Struct_noun(index_of_comp_later).getText()+" : out data port;\n";
                                                            first_new_line+=1;
                                                        }
                                                        else {
                                                            new_decl=new_decl+system_declaration[i][0].charAt(j);
                                                        }

                                                    }
                                                    system_declaration[i][0]=new_decl;
                                                }
                                            }
                                        }



                                        // For thr input port of the end comp
//        System.out.println("Start of second end");
                                        already_created_feature=false;
                                        if_present_first_index=-1;
                                        for(int i=0;i<=index_features-1;i++)
                                        {
                                            StringTokenizer st6 = new StringTokenizer(sys_features[i]," ");
                                            String comp_name=st6.nextToken();
                                            String init_struct_name=name_of_comp;
                                            if(comp_name.equals(init_struct_name))
                                            {
                                                already_created_feature=true;
                                                if_present_first_index=i;
                                            }
                                        }
//        System.out.println(ctx.struct_multinoun().getText());
                                        if(already_created_feature)
                                        {
                                            StringTokenizer st6 = new StringTokenizer(sys_features[if_present_first_index]," ");
                                            String port_name=st6.nextToken();
                                            String valid_port_name="";
                                            boolean has_found_valid_name=false;
//            System.out.println("haha");
                                            while(st6.hasMoreTokens())
                                            {
                                                port_name=st6.nextToken();
                                                if(port_name.equals(ctx.multi_flow(0).getText()+"_from_"+ctx.Struct_noun(0).toString()))
                                                {   has_found_valid_name=true;
                                                    int to_add_at_last=1;
                                                    while(port_name.equals(ctx.multi_flow(0).getText()+"_from_"+ctx.Struct_noun(0).toString()+to_add_at_last))
                                                    {
                                                        to_add_at_last+=1;
                                                    }
                                                    valid_port_name= ctx.multi_flow(0).getText()+"_from_"+ctx.Struct_noun(0).toString()+to_add_at_last;
                                                }
                                            }
//            System.out.println("hehe");
                                            if(has_found_valid_name)
                                            {
                                                sys_features[if_present_first_index]=sys_features[if_present_first_index]+" "+valid_port_name;
                                            }
                                            else {
                                                sys_features[if_present_first_index]=sys_features[if_present_first_index]+" "+ctx.multi_flow(0).getText()+"_from_"+ctx.Struct_noun(0).toString();
                                            }
                                            StringTokenizer get_last_port=new StringTokenizer(sys_features[if_present_first_index]," ");
                                            String name_of_last_port="";
                                            while(get_last_port.hasMoreTokens())
                                            {
                                                name_of_last_port=get_last_port.nextToken();
                                            }
//            System.out.println(name_of_last_port);
                                            new_decl="";
                                            int second_line=1;
                                            for(int i=0;i<=index_names-1;i++) {
                                                if (name_of_comp.equals(system_names[i])) {
//                    System.out.println(ctx.struct_multinoun().getText());
                                                    for (int j = 0; j <= system_declaration[i][0].length() - 1; j++) {
                                                        if (system_declaration[i][0].charAt(j) == '\n' && second_line == 0) {
                                                            new_decl=new_decl+"\n\t\t"+name_of_last_port+" : in data port;\n";
                                                            second_line-=1;
                                                        }
                                                        else {
                                                            new_decl=new_decl+system_declaration[i][0].charAt(j);
                                                            if(system_declaration[i][0].charAt(j) == '\n')
                                                            {
                                                                second_line-=1;
                                                            }
                                                        }

                                                    }
                                                    system_declaration[i][0]=new_decl;
                                                }
                                            }
                                        }
                                        else {
                                            sys_features[index_features] = name_of_comp;
                                            new_decl = "";
                                            sys_features[index_features] = sys_features[index_features] + " " + ctx.multi_flow(0).getText() + "_from_" + ctx.Struct_noun(0).toString();
                                            index_features += 1;
                                            first_new_line = 0;
                                            for (int i = 0; i <= index_names - 1; i++) {
                                                if (name_of_comp.equals(system_names[i])) {
                                                    for (int j = 0; j <= system_declaration[i][0].length() - 1; j++) {
                                                        if (system_declaration[i][0].charAt(j) == '\n' && first_new_line == 0) {
                                                            new_decl = new_decl + "\n\tfeatures\n";
                                                            new_decl = new_decl + "\t\t" + ctx.multi_flow(0).getText() + "_from_" + ctx.Struct_noun(0).toString() + " : in data port;\n";
                                                            first_new_line += 1;
                                                        } else {
                                                            new_decl = new_decl + system_declaration[i][0].charAt(j);
                                                        }

                                                    }
                                                    system_declaration[i][0] = new_decl;
                                                }
                                            }
                                        }

                                        copy_of_second-=1;
                                        if(copy_of_second==1)
                                        {
                                            break;
                                        }

                                    }
                                }

                            }

                            index_of_comp_already_created=-1;
                            already_created=false;
                            for(int i=0;i<=index_features-1;i++)
                            {
                                StringTokenizer st=new StringTokenizer(sys_features[i]," ");
                                String comp_name=st.nextToken();
//                        System.out.println(ctx.Struct_noun(0).toString());
//                        System.out.println(comp_name);
                                if(comp_name.equals(ctx.struct_multinoun().Struct_noun(index_of_comp_later).getText()))
                                {
                                    already_created=true;
                                    index_of_comp_already_created=i;
                                    break;
                                }
                            }
//                    System.out.println(already_created);
//                    System.out.println(ctx.getText());
                            if(already_created)
                            {
//                        System.out.println(index_of_comp_later);
//                        System.out.println(index_of_comp_later);
                                StringTokenizer st = new StringTokenizer(sys_features[index_of_comp_already_created], " ");
                                String port_name = st.nextToken();
                                String valid_port_name = "";
                                boolean has_found_valid_name = false;
                                while (st.hasMoreTokens()) {
                                    port_name=st.nextToken();
                                    if (port_name.equals(ctx.multi_flow(0).getText()+"_from_" + ctx.Struct_noun(0).toString())) {
                                        int to_add_at_last = 1;
                                        while (port_name.equals(ctx.multi_flow(0).getText()+"_from_" + ctx.Struct_noun(0).toString() + to_add_at_last)) {
                                            to_add_at_last += 1;
                                        }
                                        valid_port_name = ctx.multi_flow(0).getText()+"_from_" + ctx.Struct_noun(0).toString() + to_add_at_last;
                                        has_found_valid_name = true;
                                    }
                                }
                                if (has_found_valid_name) {
                                    sys_features[index_of_comp_already_created] = sys_features[index_of_comp_already_created] + " " + valid_port_name;
                                }
                                else {
                                    sys_features[index_of_comp_already_created] = sys_features[index_of_comp_already_created] + " " + ctx.multi_flow(0).getText()+"_from_" + ctx.Struct_noun(0).toString();
                                }
                                StringTokenizer get_last_port = new StringTokenizer(sys_features[index_of_comp_already_created], " ");
                                String name_of_last_port = "";
                                while (get_last_port.hasMoreTokens()) {
                                    name_of_last_port = get_last_port.nextToken();
                                }
//                        System.out.println(name_of_last_port+" : After while");
                                new_decl = "";
                                int second_line = 1;
                                for (int i = 0; i <= index_names - 1; i++) {
                                    if (ctx.struct_multinoun().Struct_noun(index_of_comp_later).getText().equals(system_names[i])) {
                                        for (int j = 0; j <= system_declaration[i][0].length() - 1; j++) {
                                            if (system_declaration[i][0].charAt(j) == '\n' && second_line == 0) {
                                                new_decl = new_decl + "\n\t\t" + name_of_last_port + " : in data port;\n";
//                                        System.out.println(name_of_last_port);
                                                second_line -= 1;
                                            } else {
                                                new_decl = new_decl + system_declaration[i][0].charAt(j);
                                                if (system_declaration[i][0].charAt(j) == '\n') {
                                                    second_line -= 1;
                                                }
                                            }

                                        }
                                        system_declaration[i][0] = new_decl;
                                    }
                                }
                            }
                            else {

                                sys_features[index_features]=ctx.struct_multinoun().Struct_noun(index_of_comp_later).getText();
//            System.out.println(ctx.struct_multinoun().getText());
                                new_decl = "";
                                sys_features[index_features] = sys_features[index_features] + " " + ctx.multi_flow(0).getText()+"_from_" + ctx.Struct_noun(0).toString();
                                index_features += 1;
//            System.out.println("Increased");
                                first_new_line = 0;
                                for (int i = 0; i <= index_names - 1; i++) {
                                    if (ctx.struct_multinoun().Struct_noun(index_of_comp_later).getText().equals(system_names[i])) {
                                        for (int j = 0; j <= system_declaration[i][0].length() - 1; j++) {
                                            if (system_declaration[i][0].charAt(j) == '\n' && first_new_line == 0) {
                                                new_decl = new_decl + "\n\tfeatures\n";
                                                new_decl = new_decl + "\t\t" + ctx.multi_flow(0).getText()+"_from_" + ctx.Struct_noun(0).toString() + " : in data port;\n";
                                                first_new_line += 1;
                                            } else {
                                                new_decl = new_decl + system_declaration[i][0].charAt(j);
                                            }

                                        }
                                        system_declaration[i][0] = new_decl;
                                    }
                                }
                            }

                            if(under_same_component)
                            {
                                int index_of_upper_comp=-1;
                                String upper_comp="";
                                for(int i=0;i<=index_subcomponents-1;i++)
                                {
                                    if(system_subcomponents[i].contains(ctx.Struct_noun(0).toString()) && system_subcomponents[i].contains(ctx.struct_multinoun().Struct_noun(index_of_comp_later).getText()))
                                    {
                                        StringTokenizer st=new StringTokenizer(system_subcomponents[i]," ");
                                        upper_comp=st.nextToken();
                                    }
                                }
                                for(int i=0; i<=index_names-1;i++)
                                {
                                    if(system_names[i].equals(upper_comp))
                                    {
                                        index_of_upper_comp=i;
                                    }
                                }
                                int count_of_connections=0;
                                boolean already_connections=false;
                                for(int i=0;i<=index_connections-1;i++)
                                {
                                    if(connections[i].equals(upper_comp))
                                    {
                                        already_connections=true;
                                        count_of_connections=number_of_connections[i];
                                    }
                                }
                                new_decl="";
                                int nextline_char=0;
                                String port_name_of_first="";
                                String port_name_of_second="";

                                for(int i=0;i<=index_features-1;i++)
                                {
                                    StringTokenizer st= new StringTokenizer(sys_features[i]," ");
                                    String name_of_comp_having_features=st.nextToken();
                                    if(ctx.Struct_noun(0).toString().equals(name_of_comp_having_features))
                                    {
                                        while(st.hasMoreTokens())
                                        {
                                            port_name_of_first=st.nextToken();
                                        }
                                    }
                                    else if(ctx.struct_multinoun().Struct_noun(index_of_comp_later).getText().equals(name_of_comp_having_features))
                                    {
                                        while(st.hasMoreTokens())
                                        {
                                            port_name_of_second=st.nextToken();
                                        }
                                    }
                                    else {

                                    }
                                }

                                for(int i=system_declaration[index_of_upper_comp][1].length()-1; i>=0; i--)
                                {
                                    if(system_declaration[index_of_upper_comp][1].charAt(i)=='\n' && nextline_char==1)
                                    {
                                        if(already_connections)
                                        {
                                            for(int k=0;k<=index_connections-1;k++)
                                            {
                                                if(upper_comp.equals(connections[k]))
                                                {
                                                    number_of_connections[k]+=1;
                                                    new_decl="\n\t\t"+upper_comp+count_of_connections+": port "+"this_"+ctx.Struct_noun(0).toString()+"."+port_name_of_first+"->this_"+ctx.struct_multinoun().Struct_noun(index_of_comp_later).getText()+"."+port_name_of_second+";\n" + new_decl;
                                                    update_list_flows(upper_comp, ctx.Struct_noun(0).toString(), port_name_of_first ,ctx.struct_multinoun().Struct_noun(index_of_comp_later).getText(), port_name_of_second);
                                                    break;
                                                }
                                            }
                                        }
                                        else {
                                            connections[index_connections]=upper_comp;
                                            index_connections+=1;
                                            number_of_connections[index_connections-1]=1;
                                            new_decl="\n\tconnections\n"+"\t\t"+upper_comp+"0"+": port "+"this_"+ctx.Struct_noun(0).toString()+"."+port_name_of_first+"->this_"+ctx.struct_multinoun().Struct_noun(index_of_comp_later).getText()+"."+port_name_of_second+";\n" + new_decl;
                                            update_list_flows(upper_comp, ctx.Struct_noun(0).toString(), port_name_of_first , ctx.struct_multinoun().Struct_noun(index_of_comp_later).getText(), port_name_of_second);
                                        }
                                        nextline_char+=1;
                                    }
                                    else
                                    {
                                        if(system_declaration[index_of_upper_comp][1].charAt(i)=='\n')
                                        {
                                            nextline_char+=1;
                                        }
                                        new_decl=system_declaration[index_of_upper_comp][1].charAt(i)+new_decl;
                                    }
                                }
                                system_declaration[index_of_upper_comp][1]=new_decl;
                            }
                            else {

                                //System.out.println(path_to_first);
                                //System.out.println(path_to_second);


                                number_of_int_first = 0;
                                number_of_int_second = 0;

                                split_first = new StringTokenizer(path_to_first, "-");
                                split_second = new StringTokenizer(path_to_second, "-");

                                while (split_first.hasMoreTokens()) {
                                    number_of_int_first += 1;
                                    split_first.nextToken();
                                }

                                while (split_second.hasMoreTokens()) {
                                    number_of_int_second += 1;
                                    split_second.nextToken();
                                }

                                //For intermediate systems within the path of first and second
                                if (number_of_int_first >= 3) {
                                    String ind_sys_first[] = new String[number_of_int_first - 1];
                                    int index_of_ind_sys_first = 0;
                                    split_first = new StringTokenizer(path_to_first, "-");
                                    split_first.nextToken();
                                    while (split_first.hasMoreTokens()) {
                                        ind_sys_first[index_of_ind_sys_first] = split_first.nextToken();
                                        index_of_ind_sys_first += 1;
                                    }
                                    String upper_comp = "";
                                    for (int k = 0; k <= index_of_ind_sys_first - 2; k++) {
                                        for (int l = 0; l <= index_subcomponents - 1; l++) {
                                            if (system_subcomponents[l].contains(ind_sys_first[k]) && system_subcomponents[l].contains(ind_sys_first[k + 1])) {
                                                StringTokenizer st = new StringTokenizer(system_subcomponents[l], " ");
                                                upper_comp = st.nextToken();
                                            }
                                        }

                                        int index_of_upper_comp = -1;
                                        for (int i = 0; i <= index_names - 1; i++) {
                                            if (system_names[i].equals(upper_comp)) {
                                                index_of_upper_comp = i;
                                            }
                                        }
                                        int count_of_connections = 0;
                                        boolean already_connections = false;
                                        for (int i = 0; i <= index_connections - 1; i++) {
                                            if (connections[i].equals(upper_comp)) {
                                                already_connections = true;
                                                count_of_connections = number_of_connections[i];
                                            }
                                        }
                                        new_decl = "";
                                        int nextline_char = 0;
                                        String port_name_of_first = "";
                                        String port_name_of_second = "";

                                        for (int i = 0; i <= index_features - 1; i++) {
                                            StringTokenizer st = new StringTokenizer(sys_features[i], " ");
                                            String name_of_comp_having_features = st.nextToken();
                                            String second_last_comp = "";
                                            if (ind_sys_first[k].equals(name_of_comp_having_features)) {
                                                while (st.hasMoreTokens()) {
                                                    port_name_of_first = st.nextToken();
                                                }
                                            } else if (ind_sys_first[k + 1].equals(name_of_comp_having_features)) {
                                                while (st.hasMoreTokens()) {
                                                    second_last_comp = port_name_of_second;
                                                    port_name_of_second = st.nextToken();
                                                }
                                                if(k==index_of_ind_sys_first-2)
                                                {

                                                }
                                                else {
                                                    port_name_of_second=second_last_comp;
                                                }
                                            } else {

                                            }
                                        }

                                        for (int i = system_declaration[index_of_upper_comp][1].length() - 1; i >= 0; i--) {
                                            if (system_declaration[index_of_upper_comp][1].charAt(i) == '\n' && nextline_char == 1) {
                                                if (already_connections) {
                                                    for (int m = 0; m <= index_connections - 1; m++) {
                                                        if (upper_comp.equals(connections[m])) {
                                                            number_of_connections[m] += 1;
                                                            new_decl = "\n\t\t" + upper_comp + count_of_connections + ": port " + "this_" + ind_sys_first[k+1] + "." + port_name_of_second + "->this_" + ind_sys_first[k] + "." + port_name_of_first + ";\n" + new_decl;
                                                            update_list_flows(upper_comp, ind_sys_first[k], port_name_of_first ,ind_sys_first[k+1], port_name_of_second);
                                                            break;
                                                        }
                                                    }
                                                } else {
                                                    connections[index_connections] = upper_comp;
                                                    index_connections += 1;
                                                    number_of_connections[index_connections - 1] = 1;
                                                    new_decl = "\n\tconnections\n" + "\t\t" + upper_comp + "0" + ": port " + "this_" + ind_sys_first[k+1] + "." + port_name_of_second + "->this_" + ind_sys_first[k] + "." + port_name_of_first + ";\n" + new_decl;
                                                    update_list_flows(upper_comp, ind_sys_first[k], port_name_of_first ,ind_sys_first[k+1], port_name_of_second);
                                                }
                                                nextline_char += 1;
                                            } else {
                                                if (system_declaration[index_of_upper_comp][1].charAt(i) == '\n') {
                                                    nextline_char += 1;
                                                }
                                                new_decl = system_declaration[index_of_upper_comp][1].charAt(i) + new_decl;
                                            }
                                        }
                                        system_declaration[index_of_upper_comp][1] = new_decl;
//                    System.out.println(system_declaration[index_of_upper_comp][1]);

                                    }
                                }
                                if (number_of_int_second >= 3) {
                                    String ind_sys_second[] = new String[number_of_int_second - 1];
                                    int index_of_ind_sys_second = 0;
                                    split_second = new StringTokenizer(path_to_second, "-");
                                    split_second.nextToken();
                                    while (split_second.hasMoreTokens()) {
                                        ind_sys_second[index_of_ind_sys_second] = split_second.nextToken();
                                        index_of_ind_sys_second += 1;
                                    }

                                    String upper_comp = "";
                                    for (int k = 0; k <= index_of_ind_sys_second - 2; k++) {
                                        for (int l = 0; l <= index_subcomponents - 1; l++) {
                                            if (system_subcomponents[l].contains(ind_sys_second[k]) && system_subcomponents[l].contains(ind_sys_second[k + 1])) {
                                                StringTokenizer st = new StringTokenizer(system_subcomponents[l], " ");
                                                upper_comp = st.nextToken();
                                            }
                                        }

                                        int index_of_upper_comp = -1;
                                        for (int i = 0; i <= index_names - 1; i++) {
                                            if (system_names[i].equals(upper_comp)) {
                                                index_of_upper_comp = i;
                                            }
                                        }
                                        int count_of_connections = 0;
                                        boolean already_connections = false;
                                        for (int i = 0; i <= index_connections - 1; i++) {
                                            if (connections[i].equals(upper_comp)) {
                                                already_connections = true;
                                                count_of_connections = number_of_connections[i];
                                            }
                                        }
                                        new_decl = "";
                                        int nextline_char = 0;
                                        String port_name_of_first = "";
                                        String port_name_of_second = "";

                                        for (int i = 0; i <= index_features - 1; i++) {
                                            StringTokenizer st = new StringTokenizer(sys_features[i], " ");
                                            String name_of_comp_having_features = st.nextToken();
                                            String second_last_port="";
                                            if (ind_sys_second[k].equals(name_of_comp_having_features)) {
                                                while (st.hasMoreTokens()) {
                                                    second_last_port=port_name_of_first;
                                                    port_name_of_first = st.nextToken();
                                                }
                                                port_name_of_first=second_last_port;
                                            } else if (ind_sys_second[k + 1].equals(name_of_comp_having_features)) {
                                                while (st.hasMoreTokens()) {
                                                    port_name_of_second = st.nextToken();
                                                }
                                            } else {

                                            }
                                        }

                                        for (int i = system_declaration[index_of_upper_comp][1].length() - 1; i >= 0; i--) {
                                            if (system_declaration[index_of_upper_comp][1].charAt(i) == '\n' && nextline_char == 1) {
                                                if (already_connections) {
                                                    for (int m = 0; m <= index_connections - 1; m++) {
                                                        if (upper_comp.equals(connections[m])) {
                                                            number_of_connections[m] += 1;
                                                            new_decl = "\n\t\t" + upper_comp + count_of_connections + ": port " + "this_" + ind_sys_second[k] + "." + port_name_of_first + "->this_" + ind_sys_second[k + 1] + "." + port_name_of_second + ";\n" + new_decl;
                                                            update_list_flows(upper_comp, ind_sys_second[k], port_name_of_first ,ind_sys_second[k+1], port_name_of_second);
                                                            break;
                                                        }
                                                    }
                                                } else {
                                                    connections[index_connections] = upper_comp;
                                                    index_connections += 1;
                                                    number_of_connections[index_connections - 1] = 1;
                                                    new_decl = "\n\tconnections\n" + "\t\t" + upper_comp + "0" + ": port " + "this_" + ind_sys_second[k] + "." + port_name_of_first + "->this_" + ind_sys_second[k + 1] + "." + port_name_of_second + ";\n" + new_decl;
                                                    update_list_flows(upper_comp, ind_sys_second[k], port_name_of_first ,ind_sys_second[k+1], port_name_of_second);
                                                }
                                                nextline_char += 1;
                                            } else {
                                                if (system_declaration[index_of_upper_comp][1].charAt(i) == '\n') {
                                                    nextline_char += 1;
                                                }
                                                new_decl = system_declaration[index_of_upper_comp][1].charAt(i) + new_decl;
                                            }
                                        }
                                        system_declaration[index_of_upper_comp][1] = new_decl;
                                        //System.out.println(system_declaration[index_of_upper_comp][1]);

                                    }
                                }


                                //For connections between first of p1 and first of p2
                                String comm_conn_first = "";
                                String comm_conn_second = "";
                                split_first = new StringTokenizer(path_to_first, "-");
                                split_second = new StringTokenizer(path_to_second, "-");
                                split_first.nextToken();
                                comm_conn_first = split_first.nextToken();
                                split_second.nextToken();
                                comm_conn_second = split_second.nextToken();

                                int index_of_upper_comp=-1;
                                String upper_comp="";
                                for(int i=0;i<=index_subcomponents-1;i++)
                                {
                                    if(system_subcomponents[i].contains(comm_conn_first) && system_subcomponents[i].contains(comm_conn_second))
                                    {
                                        StringTokenizer st=new StringTokenizer(system_subcomponents[i]," ");
                                        upper_comp=st.nextToken();
                                    }
                                }
                                for(int i=0; i<=index_names-1;i++)
                                {
                                    if(system_names[i].equals(upper_comp))
                                    {
                                        index_of_upper_comp=i;
                                    }
                                }
                                int count_of_connections=0;
                                boolean already_connections=false;
                                for(int i=0;i<=index_connections-1;i++)
                                {
                                    if(connections[i].equals(upper_comp))
                                    {
                                        already_connections=true;
                                        count_of_connections=number_of_connections[i];
                                    }
                                }
                                new_decl="";
                                int nextline_char=0;
                                String port_name_of_first="";
                                String port_name_of_second="";

                                for(int i=0;i<=index_features-1;i++)
                                {
                                    StringTokenizer st= new StringTokenizer(sys_features[i]," ");
                                    String name_of_comp_having_features=st.nextToken();
                                    String second_last_conn="";
                                    if(comm_conn_first.equals(name_of_comp_having_features))
                                    {
                                        while(st.hasMoreTokens())
                                        {
                                            second_last_conn=port_name_of_first;
                                            port_name_of_first=st.nextToken();
                                        }
                                        if(number_of_int_first>=3)
                                        {
                                            port_name_of_first=second_last_conn;
                                        }
                                    }
                                    else if(comm_conn_second.equals(name_of_comp_having_features))
                                    {
                                        while(st.hasMoreTokens())
                                        {
                                            port_name_of_second=st.nextToken();
                                        }
                                    }
                                    else {

                                    }
                                }


                                for(int i=system_declaration[index_of_upper_comp][1].length()-1; i>=0; i--)
                                {
                                    if(system_declaration[index_of_upper_comp][1].charAt(i)=='\n' && nextline_char==1)
                                    {
                                        if(already_connections)
                                        {
                                            for(int k=0;k<=index_connections-1;k++)
                                            {
                                                if(upper_comp.equals(connections[k]))
                                                {
                                                    number_of_connections[k]+=1;
                                                    new_decl="\n\t\t"+upper_comp+count_of_connections+": port "+"this_"+comm_conn_first+"."+port_name_of_first+"->this_"+comm_conn_second+"."+port_name_of_second+";\n" + new_decl;
                                                    update_list_flows(upper_comp, comm_conn_first, port_name_of_first , comm_conn_second, port_name_of_second);
                                                    break;
                                                }
                                            }
                                        }
                                        else {
                                            connections[index_connections]=upper_comp;
                                            index_connections+=1;
                                            number_of_connections[index_connections-1]=1;
                                            new_decl="\n\tconnections\n"+"\t\t"+upper_comp+"0"+": port "+"this_"+comm_conn_first+"."+port_name_of_first+"->this_"+comm_conn_second+"."+port_name_of_second+";\n" + new_decl;
                                            update_list_flows(upper_comp, comm_conn_first, port_name_of_first , comm_conn_second, port_name_of_second);
                                        }
                                        nextline_char+=1;
                                    }
                                    else
                                    {
                                        if(system_declaration[index_of_upper_comp][1].charAt(i)=='\n')
                                        {
                                            nextline_char+=1;
                                        }
                                        new_decl=system_declaration[index_of_upper_comp][1].charAt(i)+new_decl;
                                    }
                                }
                                system_declaration[index_of_upper_comp][1]=new_decl;

                            }

                            index_of_comp_later+=1;
                        }
                    }
                    //Connections for distributes intermediates
                }
            }
        }


        else if(func_verb_of_stmt.equals("imports"))
        {
            String path_to_first=ctx.Struct_noun(0).toString();
            String path_to_second="";
            boolean under_same_component=false;
            for(int i=0;i<=index_subcomponents-1;i++)
            {
                if(system_subcomponents[i].contains(ctx.Struct_noun(0).toString()) && system_subcomponents[i].contains(ctx.struct_multinoun().Struct_noun(0).getText()))
                {
                    under_same_component=true;
                    path_to_second=ctx.struct_multinoun().Struct_noun(0).getText();
//                                   //System.out.println("Under same component");
                }

            }
            if(!under_same_component) {
                int index_of_first_comp_upper = -1;
                boolean found_it = false;
                int index_of_second_comp_upper = -1;
                for (int i = 0; i <= index_subcomponents - 1; i++) {
                    if (system_subcomponents[i].contains(ctx.struct_multinoun().Struct_noun(0).getText())) {
                        index_of_second_comp_upper = i;
                    }
                    if (system_subcomponents[i].contains(ctx.Struct_noun(0).toString())) {
                        index_of_first_comp_upper = i;
                    }
                }

                int copy_of_second_comp = index_of_second_comp_upper;
                String second = ctx.struct_multinoun().Struct_noun(0).getText();
                while (index_of_first_comp_upper >= 0) {
                    StringTokenizer st = new StringTokenizer(system_subcomponents[index_of_first_comp_upper], " ");
                    String first = st.nextToken();
                    //System.out.println(path_to_first.contains(first));
                    //System.out.println(path_to_first);
                    if(first.equals(path_to_first))
                    {

                    }
                    else {
                        path_to_first = first + "-" + path_to_first;
                    }
                    second = ctx.struct_multinoun().Struct_noun(0).getText();
                    path_to_second = second;
                    for (int i = 0; i <= index_subcomponents - 1; i++) {
                        if (system_subcomponents[i].contains(ctx.struct_multinoun().Struct_noun(0).getText())) {
                            index_of_second_comp_upper = i;
                        }
                    }
                    while (index_of_second_comp_upper >= 0) {
                        //System.out.println(first);
                        //System.out.println(second);
                        for (int i = 0; i <= index_subcomponents - 1; i++) {
                            if (system_subcomponents[i].contains(first) && system_subcomponents[i].contains(second)) {
                                StringTokenizer st3 = new StringTokenizer(system_subcomponents[i], " ");
                                String common_comp_for_conn = st3.nextToken();

                                if(path_to_first.contains(common_comp_for_conn))
                                {

                                }
                                else {
                                    path_to_first = common_comp_for_conn + "-" + path_to_first;
                                }
                                if(path_to_second.contains(common_comp_for_conn))
                                {

                                }
                                else {
                                    path_to_second = common_comp_for_conn + "-" + path_to_second;
                                }
                                found_it = true;
                                break;
                            }
                        }
                        if (found_it) {
                            break;
                        } else {
                            if (index_of_second_comp_upper == 0) {
                                break;
                            }
                            for (int j = 0; j <= index_of_second_comp_upper; j++) {
                                boolean val=false;
                                if (system_subcomponents[j].contains(second)) {
                                    //System.out.println("Outer second");
                                    index_of_second_comp_upper = j;
                                    StringTokenizer st2 = new StringTokenizer(system_subcomponents[index_of_second_comp_upper], " ");
                                    String new_second = st2.nextToken();
                                    if(new_second.equals(second))
                                    {
                                        for (int k = 0; k <= index_of_second_comp_upper-1; k++) {
                                            //System.out.println("Inner second");
                                            if (system_subcomponents[k].contains(second)) {
                                                index_of_second_comp_upper = k;
                                                st2 = new StringTokenizer(system_subcomponents[index_of_second_comp_upper], " ");
                                                second = st2.nextToken();
                                                path_to_second = second + "-" + path_to_second;
                                                val=true;
                                                break;
                                            }
                                        }
                                    }
                                    else {
                                        second=new_second;
                                        path_to_second = second + "-" + path_to_second;
                                        val=true;
                                        break;
                                    }
                                    if(val)
                                    {
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    if (found_it) {
                        break;
                    } else {
                        if (index_of_first_comp_upper == 0) {
                            break;
                        }
                        for (int j = 0; j <= index_of_first_comp_upper; j++) {
                            //System.out.println("Outer first");
                            boolean val=false;
                            if (system_subcomponents[j].contains(first)) {
                                index_of_first_comp_upper = j;
                                StringTokenizer st2 = new StringTokenizer(system_subcomponents[index_of_first_comp_upper], " ");
                                String new_first = st2.nextToken();
                                if(first.equals(new_first)) {
                                    for (int k = 0; k <= index_of_first_comp_upper-1; k++) {
                                        //System.out.println("Inner first");
                                        if (system_subcomponents[k].contains(first)) {
                                            index_of_first_comp_upper = k;
                                            val=true;
                                            break;
                                        }
                                    }
                                }
                                else{
                                    val=true;
                                    break;
                                }
                                if(val)
                                {
                                    break;
                                }
                            }
                        }
                    }
                }

            }

            //System.out.println(path_to_first);
            //System.out.println(path_to_second);

            boolean import_from_env= ctx.struct_multinoun().getText().contains("ENV");
//            System.out.println(import_from_env);
            if(import_from_env  && !ctx.struct_multinoun().getText().contains(",") && !ctx.struct_multinoun().getText().contains("and"))
            {
                boolean already_created_feature=false;
                int if_present_first_index=-1;
                for(int i=0;i<=index_features-1;i++)
                {
                    StringTokenizer st = new StringTokenizer(sys_features[i]," ");
                    String comp_name=st.nextToken();
                    String init_struct_name=ctx.Struct_noun(0).toString();
//                    System.out.println(init_struct_name);
                    if(comp_name.equals(init_struct_name))
                    {
                        already_created_feature=true;
                        if_present_first_index=i;
                    }
                }

                if(already_created_feature)
                {
                    StringTokenizer st = new StringTokenizer(sys_features[if_present_first_index]," ");
                    String port_name=st.nextToken();
                    String valid_port_name="";
                    boolean has_found_valid_name=false;
                    while(st.hasMoreTokens())
                    {
                        port_name=st.nextToken();
                        if(port_name.equals(ctx.multi_flow(0).getText()+"_from_"+ctx.struct_multinoun().getText()))
                        {
                            int to_add_at_last=1;
                            while(port_name.equals(ctx.multi_flow(0).getText()+"_from_"+ctx.struct_multinoun().getText()+to_add_at_last))
                            {
                                to_add_at_last+=1;
                            }
                            valid_port_name= ctx.multi_flow(0).getText()+"_from_"+ctx.struct_multinoun().getText()+to_add_at_last;
                            has_found_valid_name=true;
                        }
                    }
                    if(has_found_valid_name)
                    {
                        sys_features[if_present_first_index]=sys_features[if_present_first_index]+" "+valid_port_name;
                    }
                    else {
                        sys_features[if_present_first_index]=sys_features[if_present_first_index]+" "+ctx.multi_flow(0).getText()+"_from_"+ctx.struct_multinoun().getText();
                    }
                    StringTokenizer get_last_port=new StringTokenizer(sys_features[if_present_first_index]," ");
                    String name_of_last_port="";
                    while(get_last_port.hasMoreTokens())
                    {
                        name_of_last_port=get_last_port.nextToken();
                    }
                    String new_decl="";
                    int second_line=1;
                    for(int i=0;i<=index_names-1;i++) {
                        if (ctx.Struct_noun(0).toString().equals(system_names[i])) {
                            for (int j = 0; j <= system_declaration[i][0].length() - 1; j++) {
                                if (system_declaration[i][0].charAt(j) == '\n' && second_line == 0) {
                                    new_decl=new_decl+"\n\t\t"+name_of_last_port+" : in data port;\n";
                                    second_line-=1;
                                }
                                else {
                                    new_decl=new_decl+system_declaration[i][0].charAt(j);
                                    if(system_declaration[i][0].charAt(j) == '\n')
                                    {
                                        second_line-=1;
                                    }
                                }

                            }
                            system_declaration[i][0]=new_decl;
                        }
                    }
                }
                else {
                    sys_features[index_features]=ctx.Struct_noun(0).toString();
//            System.out.println(ctx.struct_multinoun().getText());
                    String new_decl="";
                    sys_features[index_features]=sys_features[index_features]+" "+ctx.multi_flow(0).getText()+"_from_"+ctx.struct_multinoun().getText();
                    index_features+=1;
//            System.out.println("Increased");
                    int first_new_line=0;
                    for(int i=0;i<=index_names-1;i++) {
                        if (ctx.Struct_noun(0).toString().equals(system_names[i])) {
                            for (int j = 0; j <= system_declaration[i][0].length() - 1; j++) {
                                if (system_declaration[i][0].charAt(j) == '\n' && first_new_line == 0) {
                                    new_decl = new_decl + "\n\tfeatures\n";
                                    new_decl=new_decl+"\t\t"+ctx.multi_flow(0).getText()+"_from_"+ctx.struct_multinoun().getText()+" : in data port;\n";
                                    first_new_line+=1;
                                }
                                else {
                                    new_decl=new_decl+system_declaration[i][0].charAt(j);
                                }

                            }
                            system_declaration[i][0]=new_decl;
                        }
                    }
                }



                // For thr input port of the end comp
//        System.out.println("Start of second end");
                already_created_feature=false;
                if_present_first_index=-1;
                for(int i=0;i<=index_features-1;i++)
                {
                    StringTokenizer st = new StringTokenizer(sys_features[i]," ");
                    String comp_name=st.nextToken();
                    String init_struct_name=ctx.struct_multinoun().getText();
                    if(comp_name.equals(init_struct_name))
                    {
                        already_created_feature=true;
                        if_present_first_index=i;
                    }
                }
//        System.out.println(ctx.struct_multinoun().getText());
                if(already_created_feature)
                {
                    StringTokenizer st = new StringTokenizer(sys_features[if_present_first_index]," ");
                    String port_name=st.nextToken();
                    String valid_port_name="";
                    boolean has_found_valid_name=false;
//            System.out.println("haha");
                    while(st.hasMoreTokens())
                    {
                        port_name=st.nextToken();
                        if(port_name.equals(ctx.multi_flow(0).getText()+"_to_"+ctx.Struct_noun(0).toString()))
                        {   has_found_valid_name=true;
                            int to_add_at_last=1;
                            while(port_name.equals(ctx.multi_flow(0).getText()+"_to_"+ctx.Struct_noun(0).toString()+to_add_at_last))
                            {
                                to_add_at_last+=1;
                            }
                            valid_port_name= ctx.multi_flow(0).getText()+"_to_"+ctx.Struct_noun(0).toString()+to_add_at_last;
                        }
                    }
//            System.out.println("hehe");
                    if(has_found_valid_name)
                    {
                        sys_features[if_present_first_index]=sys_features[if_present_first_index]+" "+valid_port_name;
                    }
                    else {
                        sys_features[if_present_first_index]=sys_features[if_present_first_index]+" "+ctx.multi_flow(0).getText()+"_to_"+ctx.Struct_noun(0).toString();
                    }
                    StringTokenizer get_last_port=new StringTokenizer(sys_features[if_present_first_index]," ");
                    String name_of_last_port="";
                    while(get_last_port.hasMoreTokens())
                    {
                        name_of_last_port=get_last_port.nextToken();
                    }
//            System.out.println(name_of_last_port);
                    String new_decl="";
                    int second_line=1;
                    for(int i=0;i<=index_names-1;i++) {
                        if (ctx.struct_multinoun().getText().equals(system_names[i])) {
//                    System.out.println(ctx.struct_multinoun().getText());
                            for (int j = 0; j <= system_declaration[i][0].length() - 1; j++) {
                                if (system_declaration[i][0].charAt(j) == '\n' && second_line == 0) {
                                    new_decl=new_decl+"\n\t\t"+name_of_last_port+" : out data port;\n";
                                    second_line-=1;
                                }
                                else {
                                    new_decl=new_decl+system_declaration[i][0].charAt(j);
                                    if(system_declaration[i][0].charAt(j) == '\n')
                                    {
                                        second_line-=1;
                                    }
                                }

                            }
                            system_declaration[i][0]=new_decl;
                        }
                    }
                }
                else {
                    sys_features[index_features]=ctx.struct_multinoun().getText();
                    String new_decl="";
                    sys_features[index_features]=sys_features[index_features]+" "+ctx.multi_flow(0).getText()+"_to_"+ctx.Struct_noun(0).toString();
                    index_features+=1;
                    int first_new_line=0;
                    for(int i=0;i<=index_names-1;i++) {
                        if (ctx.struct_multinoun().getText().equals(system_names[i])) {
                            for (int j = 0; j <= system_declaration[i][0].length() - 1; j++) {
                                if (system_declaration[i][0].charAt(j) == '\n' && first_new_line == 0) {
                                    new_decl = new_decl + "\n\tfeatures\n";
                                    new_decl=new_decl+"\t\t"+ctx.multi_flow(0).getText()+"_to_"+ctx.Struct_noun(0).toString()+" : out data port;\n";
                                    first_new_line+=1;
                                }
                                else {
                                    new_decl=new_decl+system_declaration[i][0].charAt(j);
                                }

                            }
                            system_declaration[i][0]=new_decl;
                        }
                    }
                }
                StringTokenizer split_first= new StringTokenizer(path_to_first,"-");
                int number_of_int_first=0;
                while(split_first.hasMoreTokens())
                {
                    String inter_sys=split_first.nextToken();
                    number_of_int_first+=1;
                }

                StringTokenizer split_second= new StringTokenizer(path_to_second,"-");
                int number_of_int_second=0;
                while(split_second.hasMoreTokens())
                {
                    String inter_sys=split_second.nextToken();
                    number_of_int_second+=1;
                }

                int copy_of_first=number_of_int_first;
                int copy_of_second=number_of_int_second;

                split_first= new StringTokenizer(path_to_first,"-");
                split_second=new StringTokenizer(path_to_second,"-");

                //System.out.println(path_to_first);
                //System.out.println(path_to_second);
                //System.out.println(number_of_int_first);
                //System.out.println(number_of_int_second);

                if(number_of_int_first>=3)
                {
                    while(copy_of_first>1)
                    {
                        if(copy_of_first==number_of_int_first)
                        {
                            split_first.nextToken();
                            copy_of_first-=1;
                        }
                        else
                        {
                            String name_of_comp=split_first.nextToken();
                            already_created_feature=false;
                            if_present_first_index=-1;
                            for(int i=0;i<=index_features-1;i++)
                            {
                                StringTokenizer st = new StringTokenizer(sys_features[i]," ");
                                String comp_name=st.nextToken();
                                String init_struct_name=name_of_comp;
                                if(comp_name.equals(init_struct_name))
                                {
                                    already_created_feature=true;
                                    if_present_first_index=i;
                                }
                            }

                            if(already_created_feature)
                            {
                                StringTokenizer st = new StringTokenizer(sys_features[if_present_first_index]," ");
                                String port_name=st.nextToken();
                                String valid_port_name="";
                                boolean has_found_valid_name=false;
                                while(st.hasMoreTokens())
                                {
                                    port_name=st.nextToken();
                                    if(port_name.equals(ctx.multi_flow(0).getText()+"_to_"+ctx.Struct_noun(0).toString()))
                                    {
                                        int to_add_at_last=1;
                                        while(port_name.equals(ctx.multi_flow(0).getText()+"_to_"+ctx.Struct_noun(0).toString()+to_add_at_last))
                                        {
                                            to_add_at_last+=1;
                                        }
                                        valid_port_name= ctx.multi_flow(0).getText()+"_to_"+ctx.Struct_noun(0).toString()+to_add_at_last;
                                        has_found_valid_name=true;
                                    }
                                }
                                if(has_found_valid_name)
                                {
                                    sys_features[if_present_first_index]=sys_features[if_present_first_index]+" "+valid_port_name;
                                }
                                else {
                                    sys_features[if_present_first_index]=sys_features[if_present_first_index]+" "+ctx.multi_flow(0).getText()+"_to_"+ctx.Struct_noun(0).toString();
                                }
                                StringTokenizer get_last_port=new StringTokenizer(sys_features[if_present_first_index]," ");
                                String name_of_last_port="";
                                while(get_last_port.hasMoreTokens())
                                {
                                    name_of_last_port=get_last_port.nextToken();
                                }
                                String new_decl="";
                                int second_line=1;
                                for(int i=0;i<=index_names-1;i++) {
                                    if (name_of_comp.equals(system_names[i])) {
                                        for (int j = 0; j <= system_declaration[i][0].length() - 1; j++) {
                                            if (system_declaration[i][0].charAt(j) == '\n' && second_line == 0) {
                                                new_decl=new_decl+"\n\t\t"+name_of_last_port+" : out data port;\n";
                                                second_line-=1;
                                            }
                                            else {
                                                new_decl=new_decl+system_declaration[i][0].charAt(j);
                                                if(system_declaration[i][0].charAt(j) == '\n')
                                                {
                                                    second_line-=1;
                                                }
                                            }

                                        }
                                        system_declaration[i][0]=new_decl;
                                    }
                                }
                            }
                            else {
                                sys_features[index_features]=name_of_comp;
//            System.out.println(ctx.struct_multinoun().getText());
                                String new_decl="";
                                sys_features[index_features]=sys_features[index_features]+" "+ctx.multi_flow(0).getText()+"_to_"+ctx.Struct_noun(0).toString();
                                index_features+=1;
//            System.out.println("Increased");
                                int first_new_line=0;
                                for(int i=0;i<=index_names-1;i++) {
                                    if (name_of_comp.equals(system_names[i])) {
                                        for (int j = 0; j <= system_declaration[i][0].length() - 1; j++) {
                                            if (system_declaration[i][0].charAt(j) == '\n' && first_new_line == 0) {
                                                new_decl = new_decl + "\n\tfeatures\n";
                                                new_decl=new_decl+"\t\t"+ctx.multi_flow(0).getText()+"_to_"+ctx.Struct_noun(0).toString()+" : out data port;\n";
                                                first_new_line+=1;
                                            }
                                            else {
                                                new_decl=new_decl+system_declaration[i][0].charAt(j);
                                            }

                                        }
                                        system_declaration[i][0]=new_decl;
                                    }
                                }
                            }



                            // For thr input port of the end comp
//        System.out.println("Start of second end");
                            already_created_feature=false;
                            if_present_first_index=-1;
                            for(int i=0;i<=index_features-1;i++)
                            {
                                StringTokenizer st = new StringTokenizer(sys_features[i]," ");
                                String comp_name=st.nextToken();
                                String init_struct_name=name_of_comp;
                                if(comp_name.equals(init_struct_name))
                                {
                                    already_created_feature=true;
                                    if_present_first_index=i;
                                }
                            }
//        System.out.println(ctx.struct_multinoun().getText());
                            if(already_created_feature)
                            {
                                StringTokenizer st = new StringTokenizer(sys_features[if_present_first_index]," ");
                                String port_name=st.nextToken();
                                String valid_port_name="";
                                boolean has_found_valid_name=false;
//            System.out.println("haha");
                                while(st.hasMoreTokens())
                                {
                                    port_name=st.nextToken();
                                    if(port_name.equals(ctx.multi_flow(0).getText()+"_from_"+ctx.struct_multinoun().getText()))
                                    {   has_found_valid_name=true;
                                        int to_add_at_last=1;
                                        while(port_name.equals(ctx.multi_flow(0).getText()+"_from_"+ctx.struct_multinoun().getText()+to_add_at_last))
                                        {
                                            to_add_at_last+=1;
                                        }
                                        valid_port_name= ctx.multi_flow(0).getText()+"_from_"+ctx.struct_multinoun().getText()+to_add_at_last;
                                    }
                                }
//            System.out.println("hehe");
                                if(has_found_valid_name)
                                {
                                    sys_features[if_present_first_index]=sys_features[if_present_first_index]+" "+valid_port_name;
                                }
                                else {
                                    sys_features[if_present_first_index]=sys_features[if_present_first_index]+" "+ctx.multi_flow(0).getText()+"_from_"+ctx.struct_multinoun().getText();
                                }
                                StringTokenizer get_last_port=new StringTokenizer(sys_features[if_present_first_index]," ");
                                String name_of_last_port="";
                                while(get_last_port.hasMoreTokens())
                                {
                                    name_of_last_port=get_last_port.nextToken();
                                }
//            System.out.println(name_of_last_port);
                                String new_decl="";
                                int second_line=1;
                                for(int i=0;i<=index_names-1;i++) {
                                    if (name_of_comp.equals(system_names[i])) {
//                    System.out.println(ctx.struct_multinoun().getText());
                                        for (int j = 0; j <= system_declaration[i][0].length() - 1; j++) {
                                            if (system_declaration[i][0].charAt(j) == '\n' && second_line == 0) {
                                                new_decl=new_decl+"\n\t\t"+name_of_last_port+" : in data port;\n";
                                                second_line-=1;
                                            }
                                            else {
                                                new_decl=new_decl+system_declaration[i][0].charAt(j);
                                                if(system_declaration[i][0].charAt(j) == '\n')
                                                {
                                                    second_line-=1;
                                                }
                                            }

                                        }
                                        system_declaration[i][0]=new_decl;
                                    }
                                }
                            }
                            else {
                                sys_features[index_features] = name_of_comp;
                                String new_decl = "";
                                sys_features[index_features] = sys_features[index_features] + " " + ctx.multi_flow(0).getText() + "_from_" + ctx.struct_multinoun().getText();
                                index_features += 1;
                                int first_new_line = 0;
                                for (int i = 0; i <= index_names - 1; i++) {
                                    if (name_of_comp.equals(system_names[i])) {
                                        for (int j = 0; j <= system_declaration[i][0].length() - 1; j++) {
                                            if (system_declaration[i][0].charAt(j) == '\n' && first_new_line == 0) {
                                                new_decl = new_decl + "\n\tfeatures\n";
                                                new_decl = new_decl + "\t\t" + ctx.multi_flow(0).getText() + "_from_" + ctx.struct_multinoun().getText() + " : in data port;\n";
                                                first_new_line += 1;
                                            } else {
                                                new_decl = new_decl + system_declaration[i][0].charAt(j);
                                            }

                                        }
                                        system_declaration[i][0] = new_decl;
                                    }
                                }
                            }

                            copy_of_first-=1;
                            if(copy_of_first==1)
                            {
                                break;
                            }

                        }
                    }
                }

                if(number_of_int_second>=3)
                {
                    while(copy_of_second>1)
                    {
                        if(copy_of_second==number_of_int_second)
                        {
                            split_second.nextToken();
                            copy_of_second-=1;
                            //System.out.println("Skip first");
                        }
                        else
                        {
                            String name_of_comp=split_second.nextToken();
                            //System.out.println(name_of_comp);
                            already_created_feature=false;
                            if_present_first_index=-1;
                            for(int i=0;i<=index_features-1;i++)
                            {
                                StringTokenizer st = new StringTokenizer(sys_features[i]," ");
                                String comp_name=st.nextToken();
                                String init_struct_name=name_of_comp;
                                if(comp_name.equals(init_struct_name))
                                {
                                    already_created_feature=true;
                                    if_present_first_index=i;
                                }
                            }

                            if(already_created_feature)
                            {
                                StringTokenizer st = new StringTokenizer(sys_features[if_present_first_index]," ");
                                String port_name=st.nextToken();
                                String valid_port_name="";
                                boolean has_found_valid_name=false;
                                while(st.hasMoreTokens())
                                {
                                    port_name=st.nextToken();
                                    if(port_name.equals(ctx.multi_flow(0).getText()+"_to_"+ctx.Struct_noun(0).toString()))
                                    {
                                        int to_add_at_last=1;
                                        while(port_name.equals(ctx.multi_flow(0).getText()+"_to_"+ctx.Struct_noun(0).toString()+to_add_at_last))
                                        {
                                            to_add_at_last+=1;
                                        }
                                        valid_port_name= ctx.multi_flow(0).getText()+"_to_"+ctx.Struct_noun(0).toString()+to_add_at_last;
                                        has_found_valid_name=true;
                                    }
                                }
                                if(has_found_valid_name)
                                {
                                    sys_features[if_present_first_index]=sys_features[if_present_first_index]+" "+valid_port_name;
                                }
                                else {
                                    sys_features[if_present_first_index]=sys_features[if_present_first_index]+" "+ctx.multi_flow(0).getText()+"_to_"+ctx.Struct_noun(0).toString();
                                }
                                StringTokenizer get_last_port=new StringTokenizer(sys_features[if_present_first_index]," ");
                                String name_of_last_port="";
                                while(get_last_port.hasMoreTokens())
                                {
                                    name_of_last_port=get_last_port.nextToken();
                                }
                                String new_decl="";
                                int second_line=1;
                                for(int i=0;i<=index_names-1;i++) {
                                    if (name_of_comp.equals(system_names[i])) {
                                        for (int j = 0; j <= system_declaration[i][0].length() - 1; j++) {
                                            if (system_declaration[i][0].charAt(j) == '\n' && second_line == 0) {
                                                new_decl=new_decl+"\n\t\t"+name_of_last_port+" : out data port;\n";
                                                second_line-=1;
                                            }
                                            else {
                                                new_decl=new_decl+system_declaration[i][0].charAt(j);
                                                if(system_declaration[i][0].charAt(j) == '\n')
                                                {
                                                    second_line-=1;
                                                }
                                            }

                                        }
                                        system_declaration[i][0]=new_decl;
                                    }
                                }
                            }
                            else {
                                sys_features[index_features]=name_of_comp;
//            System.out.println(ctx.struct_multinoun().getText());
                                String new_decl="";
                                sys_features[index_features]=sys_features[index_features]+" "+ctx.multi_flow(0).getText()+"_to_"+ctx.Struct_noun(0).toString();
                                index_features+=1;
//            System.out.println("Increased");
                                int first_new_line=0;
                                for(int i=0;i<=index_names-1;i++) {
                                    if (name_of_comp.equals(system_names[i])) {
                                        for (int j = 0; j <= system_declaration[i][0].length() - 1; j++) {
                                            if (system_declaration[i][0].charAt(j) == '\n' && first_new_line == 0) {
                                                new_decl = new_decl + "\n\tfeatures\n";
                                                new_decl=new_decl+"\t\t"+ctx.multi_flow(0).getText()+"_to_"+ctx.Struct_noun(0).toString()+" : out data port;\n";
                                                first_new_line+=1;
                                            }
                                            else {
                                                new_decl=new_decl+system_declaration[i][0].charAt(j);
                                            }

                                        }
                                        system_declaration[i][0]=new_decl;
                                    }
                                }
                            }



                            // For thr input port of the end comp
//        System.out.println("Start of second end");
                            already_created_feature=false;
                            if_present_first_index=-1;
                            for(int i=0;i<=index_features-1;i++)
                            {
                                StringTokenizer st = new StringTokenizer(sys_features[i]," ");
                                String comp_name=st.nextToken();
                                String init_struct_name=name_of_comp;
                                if(comp_name.equals(init_struct_name))
                                {
                                    already_created_feature=true;
                                    if_present_first_index=i;
                                }
                            }
//        System.out.println(ctx.struct_multinoun().getText());
                            if(already_created_feature)
                            {
                                StringTokenizer st = new StringTokenizer(sys_features[if_present_first_index]," ");
                                String port_name=st.nextToken();
                                String valid_port_name="";
                                boolean has_found_valid_name=false;
//            System.out.println("haha");
                                while(st.hasMoreTokens())
                                {
                                    port_name=st.nextToken();
                                    if(port_name.equals(ctx.multi_flow(0).getText()+"_from_"+ctx.struct_multinoun().getText()))
                                    {   has_found_valid_name=true;
                                        int to_add_at_last=1;
                                        while(port_name.equals(ctx.multi_flow(0).getText()+"_from_"+ctx.struct_multinoun().getText()+to_add_at_last))
                                        {
                                            to_add_at_last+=1;
                                        }
                                        valid_port_name= ctx.multi_flow(0).getText()+"_from_"+ctx.struct_multinoun().getText()+to_add_at_last;
                                    }
                                }
//            System.out.println("hehe");
                                if(has_found_valid_name)
                                {
                                    sys_features[if_present_first_index]=sys_features[if_present_first_index]+" "+valid_port_name;
                                }
                                else {
                                    sys_features[if_present_first_index]=sys_features[if_present_first_index]+" "+ctx.multi_flow(0).getText()+"_from_"+ctx.struct_multinoun().getText();
                                }
                                StringTokenizer get_last_port=new StringTokenizer(sys_features[if_present_first_index]," ");
                                String name_of_last_port="";
                                while(get_last_port.hasMoreTokens())
                                {
                                    name_of_last_port=get_last_port.nextToken();
                                }
//            System.out.println(name_of_last_port);
                                String new_decl="";
                                int second_line=1;
                                for(int i=0;i<=index_names-1;i++) {
                                    if (name_of_comp.equals(system_names[i])) {
//                    System.out.println(ctx.struct_multinoun().getText());
                                        for (int j = 0; j <= system_declaration[i][0].length() - 1; j++) {
                                            if (system_declaration[i][0].charAt(j) == '\n' && second_line == 0) {
                                                new_decl=new_decl+"\n\t\t"+name_of_last_port+" : in data port;\n";
                                                second_line-=1;
                                            }
                                            else {
                                                new_decl=new_decl+system_declaration[i][0].charAt(j);
                                                if(system_declaration[i][0].charAt(j) == '\n')
                                                {
                                                    second_line-=1;
                                                }
                                            }

                                        }
                                        system_declaration[i][0]=new_decl;
                                    }
                                }
                            }
                            else {
                                sys_features[index_features] = name_of_comp;
                                String new_decl = "";
                                sys_features[index_features] = sys_features[index_features] + " " + ctx.multi_flow(0).getText() + "_from_" +ctx.struct_multinoun().getText();
                                index_features += 1;
                                int first_new_line = 0;
                                for (int i = 0; i <= index_names - 1; i++) {
                                    if (name_of_comp.equals(system_names[i])) {
                                        for (int j = 0; j <= system_declaration[i][0].length() - 1; j++) {
                                            if (system_declaration[i][0].charAt(j) == '\n' && first_new_line == 0) {
                                                new_decl = new_decl + "\n\tfeatures\n";
                                                new_decl = new_decl + "\t\t" + ctx.multi_flow(0).getText() + "_from_" + ctx.struct_multinoun().getText() + " : in data port;\n";
                                                first_new_line += 1;
                                            } else {
                                                new_decl = new_decl + system_declaration[i][0].charAt(j);
                                            }

                                        }
                                        system_declaration[i][0] = new_decl;
                                    }
                                }
                            }

                            copy_of_second-=1;
                            if(copy_of_second==1)
                            {
                                break;
                            }

                        }
                    }

                }

                if(under_same_component)
                {
                    int index_of_upper_comp=-1;
                    String upper_comp="";
                    for(int i=0;i<=index_subcomponents-1;i++)
                    {
                        if(system_subcomponents[i].contains(ctx.Struct_noun(0).toString()) && system_subcomponents[i].contains(ctx.struct_multinoun().Struct_noun(0).getText()))
                        {
                            StringTokenizer st=new StringTokenizer(system_subcomponents[i]," ");
                            upper_comp=st.nextToken();
                        }
                    }
                    for(int i=0; i<=index_names-1;i++)
                    {
                        if(system_names[i].equals(upper_comp))
                        {
                            index_of_upper_comp=i;
                        }
                    }
                    int count_of_connections=0;
                    boolean already_connections=false;
                    for(int i=0;i<=index_connections-1;i++)
                    {
                        if(connections[i].equals(upper_comp))
                        {
                            already_connections=true;
                            count_of_connections=number_of_connections[i];
                        }
                    }
                    String new_decl="";
                    int nextline_char=0;
                    String port_name_of_first="";
                    String port_name_of_second="";

                    for(int i=0;i<=index_features-1;i++)
                    {
                        StringTokenizer st= new StringTokenizer(sys_features[i]," ");
                        String name_of_comp_having_features=st.nextToken();
                        if(ctx.Struct_noun(0).toString().equals(name_of_comp_having_features))
                        {
                            while(st.hasMoreTokens())
                            {
                                port_name_of_first=st.nextToken();
                            }
                        }
                        else if(ctx.struct_multinoun().Struct_noun(0).getText().equals(name_of_comp_having_features))
                        {
                            while(st.hasMoreTokens())
                            {
                                port_name_of_second=st.nextToken();
                            }
                        }
                        else {

                        }
                    }


                    for(int i=system_declaration[index_of_upper_comp][1].length()-1; i>=0; i--)
                    {
                        if(system_declaration[index_of_upper_comp][1].charAt(i)=='\n' && nextline_char==1)
                        {
                            if(already_connections)
                            {
                                for(int k=0;k<=index_connections-1;k++)
                                {
                                    if(upper_comp.equals(connections[k]))
                                    {
                                        number_of_connections[k]+=1;
                                        new_decl="\n\t\t"+upper_comp+count_of_connections+": port "+"this_"+ctx.struct_multinoun().Struct_noun(0).getText()+"."+port_name_of_second+"->this_"+ctx.Struct_noun(0).toString()+"."+port_name_of_first+";\n" + new_decl;
                                        update_list_flows(upper_comp, ctx.Struct_noun(0).toString(), port_name_of_first ,ctx.struct_multinoun().Struct_noun(0).getText(), port_name_of_second);
                                        break;
                                    }
                                }
                            }
                            else {
                                connections[index_connections]=upper_comp;
                                index_connections+=1;
                                number_of_connections[index_connections-1]=1;
                                new_decl="\n\tconnections\n"+"\t\t"+upper_comp+"0"+": port "+"this_"+ctx.struct_multinoun().Struct_noun(0).getText()+"."+port_name_of_second+"->this_"+ctx.Struct_noun(0).toString()+"."+port_name_of_first+";\n" + new_decl;
                                update_list_flows(upper_comp, ctx.Struct_noun(0).toString(), port_name_of_first , ctx.struct_multinoun().Struct_noun(0).getText(), port_name_of_second);
                            }
                            nextline_char+=1;
                        }
                        else
                        {
                            if(system_declaration[index_of_upper_comp][1].charAt(i)=='\n')
                            {
                                nextline_char+=1;
                            }
                            new_decl=system_declaration[index_of_upper_comp][1].charAt(i)+new_decl;
                        }
                    }
                    system_declaration[index_of_upper_comp][1]=new_decl;
                }
                else {

                    //System.out.println(path_to_first);
                    //System.out.println(path_to_second);


                    number_of_int_first = 0;
                    number_of_int_second = 0;

                    split_first = new StringTokenizer(path_to_first, "-");
                    split_second = new StringTokenizer(path_to_second, "-");

                    while (split_first.hasMoreTokens()) {
                        number_of_int_first += 1;
                        split_first.nextToken();
                    }

                    while (split_second.hasMoreTokens()) {
                        number_of_int_second += 1;
                        split_second.nextToken();
                    }

                    //For intermediate systems within the path of first and second
                    if (number_of_int_first >= 3) {
                        String ind_sys_first[] = new String[number_of_int_first - 1];
                        int index_of_ind_sys_first = 0;
                        split_first = new StringTokenizer(path_to_first, "-");
                        split_first.nextToken();
                        while (split_first.hasMoreTokens()) {
                            ind_sys_first[index_of_ind_sys_first] = split_first.nextToken();
                            index_of_ind_sys_first += 1;
                        }
                        String upper_comp = "";
                        for (int k = 0; k <= index_of_ind_sys_first - 2; k++) {
                            for (int l = 0; l <= index_subcomponents - 1; l++) {
                                if (system_subcomponents[l].contains(ind_sys_first[k]) && system_subcomponents[l].contains(ind_sys_first[k + 1])) {
                                    StringTokenizer st = new StringTokenizer(system_subcomponents[l], " ");
                                    upper_comp = st.nextToken();
                                }
                            }

                            int index_of_upper_comp = -1;
                            for (int i = 0; i <= index_names - 1; i++) {
                                if (system_names[i].equals(upper_comp)) {
                                    index_of_upper_comp = i;
                                }
                            }
                            int count_of_connections = 0;
                            boolean already_connections = false;
                            for (int i = 0; i <= index_connections - 1; i++) {
                                if (connections[i].equals(upper_comp)) {
                                    already_connections = true;
                                    count_of_connections = number_of_connections[i];
                                }
                            }
                            String new_decl = "";
                            int nextline_char = 0;
                            String port_name_of_first = "";
                            String port_name_of_second = "";

                            for (int i = 0; i <= index_features - 1; i++) {
                                StringTokenizer st = new StringTokenizer(sys_features[i], " ");
                                String name_of_comp_having_features = st.nextToken();
                                String second_last_comp = "";
                                if (ind_sys_first[k].equals(name_of_comp_having_features)) {
                                    while (st.hasMoreTokens()) {
                                        second_last_comp = port_name_of_first;
                                        port_name_of_first = st.nextToken();
                                    }
                                    port_name_of_first = second_last_comp;
                                } else if (ind_sys_first[k + 1].equals(name_of_comp_having_features)) {
                                    while (st.hasMoreTokens()) {
                                        port_name_of_second = st.nextToken();
                                    }

                                } else {

                                }
                            }

                            for (int i = system_declaration[index_of_upper_comp][1].length() - 1; i >= 0; i--) {
                                if (system_declaration[index_of_upper_comp][1].charAt(i) == '\n' && nextline_char == 1) {
                                    if (already_connections) {
                                        for (int m = 0; m <= index_connections - 1; m++) {
                                            if (upper_comp.equals(connections[m])) {
                                                number_of_connections[m] += 1;
                                                new_decl = "\n\t\t" + upper_comp + count_of_connections + ": port " + "this_" + ind_sys_first[k] + "." + port_name_of_first + "->this_" + ind_sys_first[k+1] + "." + port_name_of_second + ";\n" + new_decl;
                                                update_list_flows(upper_comp, ind_sys_first[k], port_name_of_first ,ind_sys_first[k+1], port_name_of_second);
                                                break;
                                            }
                                        }
                                    } else {
                                        connections[index_connections] = upper_comp;
                                        index_connections += 1;
                                        number_of_connections[index_connections - 1] = 1;
                                        new_decl = "\n\tconnections\n" + "\t\t" + upper_comp + "0" + ": port " + "this_" + ind_sys_first[k] + "." + port_name_of_first + "->this_" + ind_sys_first[k+1] + "." + port_name_of_second + ";\n" + new_decl;
                                        update_list_flows(upper_comp, ind_sys_first[k], port_name_of_first ,ind_sys_first[k+1], port_name_of_second);
                                    }
                                    nextline_char += 1;
                                } else {
                                    if (system_declaration[index_of_upper_comp][1].charAt(i) == '\n') {
                                        nextline_char += 1;
                                    }
                                    new_decl = system_declaration[index_of_upper_comp][1].charAt(i) + new_decl;
                                }
                            }
                            system_declaration[index_of_upper_comp][1] = new_decl;
//                    System.out.println(system_declaration[index_of_upper_comp][1]);

                        }
                    }
                    if (number_of_int_second >= 3) {
                        String ind_sys_second[] = new String[number_of_int_second - 1];
                        int index_of_ind_sys_second = 0;
                        split_second = new StringTokenizer(path_to_second, "-");
                        split_second.nextToken();
                        while (split_second.hasMoreTokens()) {
                            ind_sys_second[index_of_ind_sys_second] = split_second.nextToken();
                            index_of_ind_sys_second += 1;
                        }

                        String upper_comp = "";
                        for (int k = 0; k <= index_of_ind_sys_second - 2; k++) {
                            for (int l = 0; l <= index_subcomponents - 1; l++) {
                                if (system_subcomponents[l].contains(ind_sys_second[k]) && system_subcomponents[l].contains(ind_sys_second[k + 1])) {
                                    StringTokenizer st = new StringTokenizer(system_subcomponents[l], " ");
                                    upper_comp = st.nextToken();
                                }
                            }

                            int index_of_upper_comp = -1;
                            for (int i = 0; i <= index_names - 1; i++) {
                                if (system_names[i].equals(upper_comp)) {
                                    index_of_upper_comp = i;
                                }
                            }
                            int count_of_connections = 0;
                            boolean already_connections = false;
                            for (int i = 0; i <= index_connections - 1; i++) {
                                if (connections[i].equals(upper_comp)) {
                                    already_connections = true;
                                    count_of_connections = number_of_connections[i];
                                }
                            }
                            String new_decl = "";
                            int nextline_char = 0;
                            String port_name_of_first = "";
                            String port_name_of_second = "";

                            for (int i = 0; i <= index_features - 1; i++) {
                                StringTokenizer st = new StringTokenizer(sys_features[i], " ");
                                String name_of_comp_having_features = st.nextToken();
                                String second_last_port="";
                                if (ind_sys_second[k].equals(name_of_comp_having_features)) {
                                    while (st.hasMoreTokens()) {
                                        port_name_of_first = st.nextToken();
                                    }
                                } else if (ind_sys_second[k + 1].equals(name_of_comp_having_features)) {
                                    while (st.hasMoreTokens()) {
                                        second_last_port=port_name_of_second;
                                        port_name_of_second = st.nextToken();
                                    }
                                    if(k==index_of_ind_sys_second-2)
                                    {

                                    }
                                    else {
                                        port_name_of_second=second_last_port;
                                    }
                                } else {

                                }
                            }

                            for (int i = system_declaration[index_of_upper_comp][1].length() - 1; i >= 0; i--) {
                                if (system_declaration[index_of_upper_comp][1].charAt(i) == '\n' && nextline_char == 1) {
                                    if (already_connections) {
                                        for (int m = 0; m <= index_connections - 1; m++) {
                                            if (upper_comp.equals(connections[m])) {
                                                number_of_connections[m] += 1;
                                                new_decl = "\n\t\t" + upper_comp + count_of_connections + ": port " + "this_" + ind_sys_second[k+1] + "." + port_name_of_second + "->this_" + ind_sys_second[k] + "." + port_name_of_first + ";\n" + new_decl;
                                                update_list_flows(upper_comp, ind_sys_second[k], port_name_of_first ,ind_sys_second[k+1], port_name_of_second);
                                                break;
                                            }
                                        }
                                    } else {
                                        connections[index_connections] = upper_comp;
                                        index_connections += 1;
                                        number_of_connections[index_connections - 1] = 1;
                                        new_decl = "\n\tconnections\n" + "\t\t" + upper_comp + "0" + ": port " + "this_" + ind_sys_second[k+1] + "." + port_name_of_second + "->this_" + ind_sys_second[k] + "." + port_name_of_first + ";\n" + new_decl;
                                        update_list_flows(upper_comp, ind_sys_second[k], port_name_of_first ,ind_sys_second[k+1], port_name_of_second);
                                    }
                                    nextline_char += 1;
                                } else {
                                    if (system_declaration[index_of_upper_comp][1].charAt(i) == '\n') {
                                        nextline_char += 1;
                                    }
                                    new_decl = system_declaration[index_of_upper_comp][1].charAt(i) + new_decl;
                                }
                            }
                            system_declaration[index_of_upper_comp][1] = new_decl;
                            //System.out.println(system_declaration[index_of_upper_comp][1]);

                        }
                    }


                    //For connections between first of p1 and first of p2
                    String comm_conn_first = "";
                    String comm_conn_second = "";
                    split_first = new StringTokenizer(path_to_first, "-");
                    split_second = new StringTokenizer(path_to_second, "-");
                    split_first.nextToken();
                    comm_conn_first = split_first.nextToken();
                    split_second.nextToken();
                    comm_conn_second = split_second.nextToken();

                    int index_of_upper_comp=-1;
                    String upper_comp="";
                    for(int i=0;i<=index_subcomponents-1;i++)
                    {
                        if(system_subcomponents[i].contains(comm_conn_first) && system_subcomponents[i].contains(comm_conn_second))
                        {
                            StringTokenizer st=new StringTokenizer(system_subcomponents[i]," ");
                            upper_comp=st.nextToken();
                        }
                    }
                    for(int i=0; i<=index_names-1;i++)
                    {
                        if(system_names[i].equals(upper_comp))
                        {
                            index_of_upper_comp=i;
                        }
                    }
                    int count_of_connections=0;
                    boolean already_connections=false;
                    for(int i=0;i<=index_connections-1;i++)
                    {
                        if(connections[i].equals(upper_comp))
                        {
                            already_connections=true;
                            count_of_connections=number_of_connections[i];
                        }
                    }
                    String new_decl="";
                    int nextline_char=0;
                    String port_name_of_first="";
                    String port_name_of_second="";

                    for(int i=0;i<=index_features-1;i++)
                    {
                        StringTokenizer st= new StringTokenizer(sys_features[i]," ");
                        String name_of_comp_having_features=st.nextToken();
                        String second_last_conn_second="";
                        if(comm_conn_first.equals(name_of_comp_having_features))
                        {
                            while(st.hasMoreTokens())
                            {
                                port_name_of_first=st.nextToken();
                            }
                        }
                        else if(comm_conn_second.equals(name_of_comp_having_features))
                        {
                            while(st.hasMoreTokens())
                            {
                                second_last_conn_second=port_name_of_second;
                                port_name_of_second=st.nextToken();
                            }
                            if(number_of_int_second>=3)
                            {
                                port_name_of_second=second_last_conn_second;
                            }
                        }
                        else {

                        }
                    }


                    for(int i=system_declaration[index_of_upper_comp][1].length()-1; i>=0; i--)
                    {
                        if(system_declaration[index_of_upper_comp][1].charAt(i)=='\n' && nextline_char==1)
                        {
                            if(already_connections)
                            {
                                for(int k=0;k<=index_connections-1;k++)
                                {
                                    if(upper_comp.equals(connections[k]))
                                    {
                                        number_of_connections[k]+=1;
                                        new_decl="\n\t\t"+upper_comp+count_of_connections+": port "+"this_"+comm_conn_second+"."+port_name_of_second+"->this_"+comm_conn_first+"."+port_name_of_first+";\n" + new_decl;
                                        update_list_flows(upper_comp, comm_conn_first, port_name_of_first ,comm_conn_second, port_name_of_second);
                                        break;
                                    }
                                }
                            }
                            else {
                                connections[index_connections]=upper_comp;
                                index_connections+=1;
                                number_of_connections[index_connections-1]=1;
                                new_decl="\n\tconnections\n"+"\t\t"+upper_comp+"0"+": port "+"this_"+comm_conn_second+"."+port_name_of_second+"->this_"+comm_conn_first+"."+port_name_of_first+";\n" + new_decl;
                                update_list_flows(upper_comp, comm_conn_first, port_name_of_first ,comm_conn_second, port_name_of_second);
                            }
                            nextline_char+=1;
                        }
                        else
                        {
                            if(system_declaration[index_of_upper_comp][1].charAt(i)=='\n')
                            {
                                nextline_char+=1;
                            }
                            new_decl=system_declaration[index_of_upper_comp][1].charAt(i)+new_decl;
                        }
                    }
                    system_declaration[index_of_upper_comp][1]=new_decl;

                }

            }
        }
        else if(func_verb_of_stmt.equals("exports"))
        {
            boolean export_to_env= ctx.struct_multinoun().getText().contains("ENV");
            if(export_to_env && !ctx.struct_multinoun().getText().contains(",") && !ctx.struct_multinoun().getText().contains("and"))
            {
                String path_to_first=ctx.Struct_noun(0).toString();
                String path_to_second="";
                boolean under_same_component=false;
                for(int i=0;i<=index_subcomponents-1;i++)
                {
                    if(system_subcomponents[i].contains(ctx.Struct_noun(0).toString()) && system_subcomponents[i].contains(ctx.struct_multinoun().Struct_noun(0).getText()))
                    {
                        under_same_component=true;
                        path_to_second=ctx.struct_multinoun().Struct_noun(0).getText();
//                                   //System.out.println("Under same component");
                    }

                }
                if(!under_same_component) {
                    int index_of_first_comp_upper = -1;
                    boolean found_it = false;
                    int index_of_second_comp_upper = -1;
                    for (int i = 0; i <= index_subcomponents - 1; i++) {
                        if (system_subcomponents[i].contains(ctx.struct_multinoun().Struct_noun(0).getText())) {
                            index_of_second_comp_upper = i;
                        }
                        if (system_subcomponents[i].contains(ctx.Struct_noun(0).toString())) {
                            index_of_first_comp_upper = i;
                        }
                    }

                    int copy_of_second_comp = index_of_second_comp_upper;
                    String second = ctx.struct_multinoun().Struct_noun(0).getText();
                    while (index_of_first_comp_upper >= 0) {
                        StringTokenizer st = new StringTokenizer(system_subcomponents[index_of_first_comp_upper], " ");
                        String first = st.nextToken();
                        //System.out.println(path_to_first.contains(first));
                        //System.out.println(path_to_first);
                        if(first.equals(path_to_first))
                        {

                        }
                        else {
                            path_to_first = first + "-" + path_to_first;
                        }
                        second = ctx.struct_multinoun().Struct_noun(0).getText();
                        path_to_second = second;
                        for (int i = 0; i <= index_subcomponents - 1; i++) {
                            if (system_subcomponents[i].contains(ctx.struct_multinoun().Struct_noun(0).getText())) {
                                index_of_second_comp_upper = i;
                            }
                        }
                        while (index_of_second_comp_upper >= 0) {
                            //System.out.println(first);
                            //System.out.println(second);
                            for (int i = 0; i <= index_subcomponents - 1; i++) {
                                if (system_subcomponents[i].contains(first) && system_subcomponents[i].contains(second)) {
                                    StringTokenizer st3 = new StringTokenizer(system_subcomponents[i], " ");
                                    String common_comp_for_conn = st3.nextToken();

                                    if(path_to_first.contains(common_comp_for_conn))
                                    {

                                    }
                                    else {
                                        path_to_first = common_comp_for_conn + "-" + path_to_first;
                                    }
                                    if(path_to_second.contains(common_comp_for_conn))
                                    {

                                    }
                                    else {
                                        path_to_second = common_comp_for_conn + "-" + path_to_second;
                                    }
                                    found_it = true;
                                    break;
                                }
                            }
                            if (found_it) {
                                break;
                            } else {
                                if (index_of_second_comp_upper == 0) {
                                    break;
                                }
                                for (int j = 0; j <= index_of_second_comp_upper; j++) {
                                    boolean val=false;
                                    if (system_subcomponents[j].contains(second)) {
                                        //System.out.println("Outer second");
                                        index_of_second_comp_upper = j;
                                        StringTokenizer st2 = new StringTokenizer(system_subcomponents[index_of_second_comp_upper], " ");
                                        String new_second = st2.nextToken();
                                        if(new_second.equals(second))
                                        {
                                            for (int k = 0; k <= index_of_second_comp_upper-1; k++) {
                                                //System.out.println("Inner second");
                                                if (system_subcomponents[k].contains(second)) {
                                                    index_of_second_comp_upper = k;
                                                    st2 = new StringTokenizer(system_subcomponents[index_of_second_comp_upper], " ");
                                                    second = st2.nextToken();
                                                    path_to_second = second + "-" + path_to_second;
                                                    val=true;
                                                    break;
                                                }
                                            }
                                        }
                                        else {
                                            second=new_second;
                                            path_to_second = second + "-" + path_to_second;
                                            val=true;
                                            break;
                                        }
                                        if(val)
                                        {
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                        if (found_it) {
                            break;
                        } else {
                            if (index_of_first_comp_upper == 0) {
                                break;
                            }
                            for (int j = 0; j <= index_of_first_comp_upper; j++) {
                                //System.out.println("Outer first");
                                boolean val=false;
                                if (system_subcomponents[j].contains(first)) {
                                    index_of_first_comp_upper = j;
                                    StringTokenizer st2 = new StringTokenizer(system_subcomponents[index_of_first_comp_upper], " ");
                                    String new_first = st2.nextToken();
                                    if(first.equals(new_first)) {
                                        for (int k = 0; k <= index_of_first_comp_upper-1; k++) {
                                            //System.out.println("Inner first");
                                            if (system_subcomponents[k].contains(first)) {
                                                index_of_first_comp_upper = k;
                                                val=true;
                                                break;
                                            }
                                        }
                                    }
                                    else{
                                        val=true;
                                        break;
                                    }
                                    if(val)
                                    {
                                        break;
                                    }
                                }
                            }
                        }
                    }

                }

//                System.out.println(path_to_first);
//                System.out.println(path_to_second);

                boolean already_created_feature=false;
                int if_present_first_index=-1;
                for(int i=0;i<=index_features-1;i++)
                {
                    StringTokenizer st = new StringTokenizer(sys_features[i]," ");
                    String comp_name=st.nextToken();
                    String init_struct_name=ctx.Struct_noun(0).toString();
                    if(comp_name.equals(init_struct_name))
                    {
                        already_created_feature=true;
                        if_present_first_index=i;
                    }
                }

                if(already_created_feature)
                {
                    StringTokenizer st = new StringTokenizer(sys_features[if_present_first_index]," ");
                    String port_name=st.nextToken();
                    String valid_port_name="";
                    boolean has_found_valid_name=false;
                    while(st.hasMoreTokens())
                    {
                        port_name=st.nextToken();
                        if(port_name.equals(ctx.multi_flow(0).getText()+"_to_"+ctx.struct_multinoun().getText()))
                        {
                            int to_add_at_last=1;
                            while(port_name.equals(ctx.multi_flow(0).getText()+"_to_"+ctx.struct_multinoun().getText()+to_add_at_last))
                            {
                                to_add_at_last+=1;
                            }
                            valid_port_name= ctx.multi_flow(0).getText()+"_to_"+ctx.struct_multinoun().getText()+to_add_at_last;
                            has_found_valid_name=true;
                        }
                    }
                    if(has_found_valid_name)
                    {
                        sys_features[if_present_first_index]=sys_features[if_present_first_index]+" "+valid_port_name;
                    }
                    else {
                        sys_features[if_present_first_index]=sys_features[if_present_first_index]+" "+ctx.multi_flow(0).getText()+"_to_"+ctx.struct_multinoun().getText();
                    }
                    StringTokenizer get_last_port=new StringTokenizer(sys_features[if_present_first_index]," ");
                    String name_of_last_port="";
                    while(get_last_port.hasMoreTokens())
                    {
                        name_of_last_port=get_last_port.nextToken();
                    }
                    String new_decl="";
                    int second_line=1;
                    for(int i=0;i<=index_names-1;i++) {
                        if (ctx.Struct_noun(0).toString().equals(system_names[i])) {
                            for (int j = 0; j <= system_declaration[i][0].length() - 1; j++) {
                                if (system_declaration[i][0].charAt(j) == '\n' && second_line == 0) {
                                    new_decl=new_decl+"\n\t\t"+name_of_last_port+" : out data port;\n";
                                    second_line-=1;
                                }
                                else {
                                    new_decl=new_decl+system_declaration[i][0].charAt(j);
                                    if(system_declaration[i][0].charAt(j) == '\n')
                                    {
                                        second_line-=1;
                                    }
                                }

                            }
                            system_declaration[i][0]=new_decl;
                        }
                    }
                }
                else {
                    sys_features[index_features]=ctx.Struct_noun(0).toString();
//            System.out.println(ctx.struct_multinoun().getText());
                    String new_decl="";
                    sys_features[index_features]=sys_features[index_features]+" "+ctx.multi_flow(0).getText()+"_to_"+ctx.struct_multinoun().getText();
                    index_features+=1;
//            System.out.println("Increased");
                    int first_new_line=0;
                    for(int i=0;i<=index_names-1;i++) {
                        if (ctx.Struct_noun(0).toString().equals(system_names[i])) {
                            for (int j = 0; j <= system_declaration[i][0].length() - 1; j++) {
                                if (system_declaration[i][0].charAt(j) == '\n' && first_new_line == 0) {
                                    new_decl = new_decl + "\n\tfeatures\n";
                                    new_decl=new_decl+"\t\t"+ctx.multi_flow(0).getText()+"_to_"+ctx.struct_multinoun().getText()+" : out data port;\n";
                                    first_new_line+=1;
                                }
                                else {
                                    new_decl=new_decl+system_declaration[i][0].charAt(j);
                                }

                            }
                            system_declaration[i][0]=new_decl;
                        }
                    }
                }



                // For thr input port of the end comp
//        System.out.println("Start of second end");
                already_created_feature=false;
                if_present_first_index=-1;
                for(int i=0;i<=index_features-1;i++)
                {
                    StringTokenizer st = new StringTokenizer(sys_features[i]," ");
                    String comp_name=st.nextToken();
                    String init_struct_name=ctx.struct_multinoun().getText();
                    if(comp_name.equals(init_struct_name))
                    {
                        already_created_feature=true;
                        if_present_first_index=i;
                    }
                }
//        System.out.println(ctx.struct_multinoun().getText());
                if(already_created_feature)
                {
                    StringTokenizer st = new StringTokenizer(sys_features[if_present_first_index]," ");
                    String port_name=st.nextToken();
                    String valid_port_name="";
                    boolean has_found_valid_name=false;
//            System.out.println("haha");
                    while(st.hasMoreTokens())
                    {
                        port_name=st.nextToken();
                        if(port_name.equals(ctx.multi_flow(0).getText()+"_from_"+ctx.Struct_noun(0).toString()))
                        {   has_found_valid_name=true;
                            int to_add_at_last=1;
                            while(port_name.equals(ctx.multi_flow(0).getText()+"_from_"+ctx.Struct_noun(0).toString()+to_add_at_last))
                            {
                                to_add_at_last+=1;
                            }
                            valid_port_name= ctx.multi_flow(0).getText()+"_from_"+ctx.Struct_noun(0).toString()+to_add_at_last;
                        }
                    }
//            System.out.println("hehe");
                    if(has_found_valid_name)
                    {
                        sys_features[if_present_first_index]=sys_features[if_present_first_index]+" "+valid_port_name;
                    }
                    else {
                        sys_features[if_present_first_index]=sys_features[if_present_first_index]+" "+ctx.multi_flow(0).getText()+"_from_"+ctx.Struct_noun(0).toString();
                    }
                    StringTokenizer get_last_port=new StringTokenizer(sys_features[if_present_first_index]," ");
                    String name_of_last_port="";
                    while(get_last_port.hasMoreTokens())
                    {
                        name_of_last_port=get_last_port.nextToken();
                    }
//            System.out.println(name_of_last_port);
                    String new_decl="";
                    int second_line=1;
                    for(int i=0;i<=index_names-1;i++) {
                        if (ctx.struct_multinoun().getText().equals(system_names[i])) {
//                    System.out.println(ctx.struct_multinoun().getText());
                            for (int j = 0; j <= system_declaration[i][0].length() - 1; j++) {
                                if (system_declaration[i][0].charAt(j) == '\n' && second_line == 0) {
                                    new_decl=new_decl+"\n\t\t"+name_of_last_port+" : in data port;\n";
                                    second_line-=1;
                                }
                                else {
                                    new_decl=new_decl+system_declaration[i][0].charAt(j);
                                    if(system_declaration[i][0].charAt(j) == '\n')
                                    {
                                        second_line-=1;
                                    }
                                }

                            }
                            system_declaration[i][0]=new_decl;
                        }
                    }
                }
                else {
                    sys_features[index_features]=ctx.struct_multinoun().getText();
                    String new_decl="";
                    sys_features[index_features]=sys_features[index_features]+" "+ctx.multi_flow(0).getText()+"_from_"+ctx.Struct_noun(0).toString();
                    index_features+=1;
                    int first_new_line=0;
                    for(int i=0;i<=index_names-1;i++) {
                        if (ctx.struct_multinoun().getText().equals(system_names[i])) {
                            for (int j = 0; j <= system_declaration[i][0].length() - 1; j++) {
                                if (system_declaration[i][0].charAt(j) == '\n' && first_new_line == 0) {
                                    new_decl = new_decl + "\n\tfeatures\n";
                                    new_decl=new_decl+"\t\t"+ctx.multi_flow(0).getText()+"_from_"+ctx.Struct_noun(0).toString()+" : in data port;\n";
                                    first_new_line+=1;
                                }
                                else {
                                    new_decl=new_decl+system_declaration[i][0].charAt(j);
                                }

                            }
                            system_declaration[i][0]=new_decl;
                        }
                    }
                }
                StringTokenizer split_first= new StringTokenizer(path_to_first,"-");
                int number_of_int_first=0;
                while(split_first.hasMoreTokens())
                {
                    String inter_sys=split_first.nextToken();
                    number_of_int_first+=1;
                }

                StringTokenizer split_second= new StringTokenizer(path_to_second,"-");
                int number_of_int_second=0;
                while(split_second.hasMoreTokens())
                {
                    String inter_sys=split_second.nextToken();
                    number_of_int_second+=1;
                }

                int copy_of_first=number_of_int_first;
                int copy_of_second=number_of_int_second;

                split_first= new StringTokenizer(path_to_first,"-");
                split_second=new StringTokenizer(path_to_second,"-");

                //System.out.println(path_to_first);
                //System.out.println(path_to_second);
                //System.out.println(number_of_int_first);
                //System.out.println(number_of_int_second);

                if(number_of_int_first>=3)
                {
                    while(copy_of_first>1)
                    {
                        if(copy_of_first==number_of_int_first)
                        {
                            split_first.nextToken();
                            copy_of_first-=1;
                        }
                        else
                        {
                            String name_of_comp=split_first.nextToken();
                            already_created_feature=false;
                            if_present_first_index=-1;
                            for(int i=0;i<=index_features-1;i++)
                            {
                                StringTokenizer st = new StringTokenizer(sys_features[i]," ");
                                String comp_name=st.nextToken();
                                String init_struct_name=name_of_comp;
                                if(comp_name.equals(init_struct_name))
                                {
                                    already_created_feature=true;
                                    if_present_first_index=i;
                                }
                            }

                            if(already_created_feature)
                            {
                                StringTokenizer st = new StringTokenizer(sys_features[if_present_first_index]," ");
                                String port_name=st.nextToken();
                                String valid_port_name="";
                                boolean has_found_valid_name=false;
                                while(st.hasMoreTokens())
                                {
                                    port_name=st.nextToken();
                                    if(port_name.equals(ctx.multi_flow(0).getText()+"_to_"+ctx.struct_multinoun().getText()))
                                    {
                                        int to_add_at_last=1;
                                        while(port_name.equals(ctx.multi_flow(0).getText()+"_to_"+ctx.struct_multinoun().getText()+to_add_at_last))
                                        {
                                            to_add_at_last+=1;
                                        }
                                        valid_port_name= ctx.multi_flow(0).getText()+"_to_"+ctx.struct_multinoun().getText()+to_add_at_last;
                                        has_found_valid_name=true;
                                    }
                                }
                                if(has_found_valid_name)
                                {
                                    sys_features[if_present_first_index]=sys_features[if_present_first_index]+" "+valid_port_name;
                                }
                                else {
                                    sys_features[if_present_first_index]=sys_features[if_present_first_index]+" "+ctx.multi_flow(0).getText()+"_to_"+ctx.struct_multinoun().getText();
                                }
                                StringTokenizer get_last_port=new StringTokenizer(sys_features[if_present_first_index]," ");
                                String name_of_last_port="";
                                while(get_last_port.hasMoreTokens())
                                {
                                    name_of_last_port=get_last_port.nextToken();
                                }
                                String new_decl="";
                                int second_line=1;
                                for(int i=0;i<=index_names-1;i++) {
                                    if (name_of_comp.equals(system_names[i])) {
                                        for (int j = 0; j <= system_declaration[i][0].length() - 1; j++) {
                                            if (system_declaration[i][0].charAt(j) == '\n' && second_line == 0) {
                                                new_decl=new_decl+"\n\t\t"+name_of_last_port+" : out data port;\n";
                                                second_line-=1;
                                            }
                                            else {
                                                new_decl=new_decl+system_declaration[i][0].charAt(j);
                                                if(system_declaration[i][0].charAt(j) == '\n')
                                                {
                                                    second_line-=1;
                                                }
                                            }

                                        }
                                        system_declaration[i][0]=new_decl;
                                    }
                                }
                            }
                            else {
                                sys_features[index_features]=name_of_comp;
//            System.out.println(ctx.struct_multinoun().getText());
                                String new_decl="";
                                sys_features[index_features]=sys_features[index_features]+" "+ctx.multi_flow(0).getText()+"_to_"+ctx.struct_multinoun().getText();
                                index_features+=1;
//            System.out.println("Increased");
                                int first_new_line=0;
                                for(int i=0;i<=index_names-1;i++) {
                                    if (name_of_comp.equals(system_names[i])) {
                                        for (int j = 0; j <= system_declaration[i][0].length() - 1; j++) {
                                            if (system_declaration[i][0].charAt(j) == '\n' && first_new_line == 0) {
                                                new_decl = new_decl + "\n\tfeatures\n";
                                                new_decl=new_decl+"\t\t"+ctx.multi_flow(0).getText()+"_to_"+ctx.struct_multinoun().getText()+" : out data port;\n";
                                                first_new_line+=1;
                                            }
                                            else {
                                                new_decl=new_decl+system_declaration[i][0].charAt(j);
                                            }

                                        }
                                        system_declaration[i][0]=new_decl;
                                    }
                                }
                            }



                            // For thr input port of the end comp
//        System.out.println("Start of second end");
                            already_created_feature=false;
                            if_present_first_index=-1;
                            for(int i=0;i<=index_features-1;i++)
                            {
                                StringTokenizer st = new StringTokenizer(sys_features[i]," ");
                                String comp_name=st.nextToken();
                                String init_struct_name=name_of_comp;
                                if(comp_name.equals(init_struct_name))
                                {
                                    already_created_feature=true;
                                    if_present_first_index=i;
                                }
                            }
//        System.out.println(ctx.struct_multinoun().getText());
                            if(already_created_feature)
                            {
                                StringTokenizer st = new StringTokenizer(sys_features[if_present_first_index]," ");
                                String port_name=st.nextToken();
                                String valid_port_name="";
                                boolean has_found_valid_name=false;
//            System.out.println("haha");
                                while(st.hasMoreTokens())
                                {
                                    port_name=st.nextToken();
                                    if(port_name.equals(ctx.multi_flow(0).getText()+"_from_"+ctx.Struct_noun(0).toString()))
                                    {   has_found_valid_name=true;
                                        int to_add_at_last=1;
                                        while(port_name.equals(ctx.multi_flow(0).getText()+"_from_"+ctx.Struct_noun(0).toString()+to_add_at_last))
                                        {
                                            to_add_at_last+=1;
                                        }
                                        valid_port_name= ctx.multi_flow(0).getText()+"_from_"+ctx.Struct_noun(0).toString()+to_add_at_last;
                                    }
                                }
//            System.out.println("hehe");
                                if(has_found_valid_name)
                                {
                                    sys_features[if_present_first_index]=sys_features[if_present_first_index]+" "+valid_port_name;
                                }
                                else {
                                    sys_features[if_present_first_index]=sys_features[if_present_first_index]+" "+ctx.multi_flow(0).getText()+"_from_"+ctx.Struct_noun(0).toString();
                                }
                                StringTokenizer get_last_port=new StringTokenizer(sys_features[if_present_first_index]," ");
                                String name_of_last_port="";
                                while(get_last_port.hasMoreTokens())
                                {
                                    name_of_last_port=get_last_port.nextToken();
                                }
//            System.out.println(name_of_last_port);
                                String new_decl="";
                                int second_line=1;
                                for(int i=0;i<=index_names-1;i++) {
                                    if (name_of_comp.equals(system_names[i])) {
//                    System.out.println(ctx.struct_multinoun().getText());
                                        for (int j = 0; j <= system_declaration[i][0].length() - 1; j++) {
                                            if (system_declaration[i][0].charAt(j) == '\n' && second_line == 0) {
                                                new_decl=new_decl+"\n\t\t"+name_of_last_port+" : in data port;\n";
                                                second_line-=1;
                                            }
                                            else {
                                                new_decl=new_decl+system_declaration[i][0].charAt(j);
                                                if(system_declaration[i][0].charAt(j) == '\n')
                                                {
                                                    second_line-=1;
                                                }
                                            }

                                        }
                                        system_declaration[i][0]=new_decl;
                                    }
                                }
                            }
                            else {
                                sys_features[index_features] = name_of_comp;
                                String new_decl = "";
                                sys_features[index_features] = sys_features[index_features] + " " + ctx.multi_flow(0).getText() + "_from_" + ctx.Struct_noun(0).toString();
                                index_features += 1;
                                int first_new_line = 0;
                                for (int i = 0; i <= index_names - 1; i++) {
                                    if (name_of_comp.equals(system_names[i])) {
                                        for (int j = 0; j <= system_declaration[i][0].length() - 1; j++) {
                                            if (system_declaration[i][0].charAt(j) == '\n' && first_new_line == 0) {
                                                new_decl = new_decl + "\n\tfeatures\n";
                                                new_decl = new_decl + "\t\t" + ctx.multi_flow(0).getText() + "_from_" + ctx.Struct_noun(0).toString() + " : in data port;\n";
                                                first_new_line += 1;
                                            } else {
                                                new_decl = new_decl + system_declaration[i][0].charAt(j);
                                            }

                                        }
                                        system_declaration[i][0] = new_decl;
                                    }
                                }
                            }

                            copy_of_first-=1;
                            if(copy_of_first==1)
                            {
                                break;
                            }

                        }
                    }
                }

                if(number_of_int_second>=3)
                {
                    while(copy_of_second>1)
                    {
                        if(copy_of_second==number_of_int_second)
                        {
                            split_second.nextToken();
                            copy_of_second-=1;
                            //System.out.println("Skip first");
                        }
                        else
                        {
                            String name_of_comp=split_second.nextToken();
                            //System.out.println(name_of_comp);
                            already_created_feature=false;
                            if_present_first_index=-1;
                            for(int i=0;i<=index_features-1;i++)
                            {
                                StringTokenizer st = new StringTokenizer(sys_features[i]," ");
                                String comp_name=st.nextToken();
                                String init_struct_name=name_of_comp;
                                if(comp_name.equals(init_struct_name))
                                {
                                    already_created_feature=true;
                                    if_present_first_index=i;
                                }
                            }

                            if(already_created_feature)
                            {
                                StringTokenizer st = new StringTokenizer(sys_features[if_present_first_index]," ");
                                String port_name=st.nextToken();
                                String valid_port_name="";
                                boolean has_found_valid_name=false;
                                while(st.hasMoreTokens())
                                {
                                    port_name=st.nextToken();
                                    if(port_name.equals(ctx.multi_flow(0).getText()+"_to_"+ctx.struct_multinoun().getText()))
                                    {
                                        int to_add_at_last=1;
                                        while(port_name.equals(ctx.multi_flow(0).getText()+"_to_"+ctx.struct_multinoun().getText()+to_add_at_last))
                                        {
                                            to_add_at_last+=1;
                                        }
                                        valid_port_name= ctx.multi_flow(0).getText()+"_to_"+ctx.struct_multinoun().getText()+to_add_at_last;
                                        has_found_valid_name=true;
                                    }
                                }
                                if(has_found_valid_name)
                                {
                                    sys_features[if_present_first_index]=sys_features[if_present_first_index]+" "+valid_port_name;
                                }
                                else {
                                    sys_features[if_present_first_index]=sys_features[if_present_first_index]+" "+ctx.multi_flow(0).getText()+"_to_"+ctx.struct_multinoun().getText();
                                }
                                StringTokenizer get_last_port=new StringTokenizer(sys_features[if_present_first_index]," ");
                                String name_of_last_port="";
                                while(get_last_port.hasMoreTokens())
                                {
                                    name_of_last_port=get_last_port.nextToken();
                                }
                                String new_decl="";
                                int second_line=1;
                                for(int i=0;i<=index_names-1;i++) {
                                    if (name_of_comp.equals(system_names[i])) {
                                        for (int j = 0; j <= system_declaration[i][0].length() - 1; j++) {
                                            if (system_declaration[i][0].charAt(j) == '\n' && second_line == 0) {
                                                new_decl=new_decl+"\n\t\t"+name_of_last_port+" : out data port;\n";
                                                second_line-=1;
                                            }
                                            else {
                                                new_decl=new_decl+system_declaration[i][0].charAt(j);
                                                if(system_declaration[i][0].charAt(j) == '\n')
                                                {
                                                    second_line-=1;
                                                }
                                            }

                                        }
                                        system_declaration[i][0]=new_decl;
                                    }
                                }
                            }
                            else {
                                sys_features[index_features]=name_of_comp;
//            System.out.println(ctx.struct_multinoun().getText());
                                String new_decl="";
                                sys_features[index_features]=sys_features[index_features]+" "+ctx.multi_flow(0).getText()+"_to_"+ctx.struct_multinoun().getText();
                                index_features+=1;
//            System.out.println("Increased");
                                int first_new_line=0;
                                for(int i=0;i<=index_names-1;i++) {
                                    if (name_of_comp.equals(system_names[i])) {
                                        for (int j = 0; j <= system_declaration[i][0].length() - 1; j++) {
                                            if (system_declaration[i][0].charAt(j) == '\n' && first_new_line == 0) {
                                                new_decl = new_decl + "\n\tfeatures\n";
                                                new_decl=new_decl+"\t\t"+ctx.multi_flow(0).getText()+"_to_"+ctx.struct_multinoun().getText()+" : out data port;\n";
                                                first_new_line+=1;
                                            }
                                            else {
                                                new_decl=new_decl+system_declaration[i][0].charAt(j);
                                            }

                                        }
                                        system_declaration[i][0]=new_decl;
                                    }
                                }
                            }



                            // For thr input port of the end comp
//        System.out.println("Start of second end");
                            already_created_feature=false;
                            if_present_first_index=-1;
                            for(int i=0;i<=index_features-1;i++)
                            {
                                StringTokenizer st = new StringTokenizer(sys_features[i]," ");
                                String comp_name=st.nextToken();
                                String init_struct_name=name_of_comp;
                                if(comp_name.equals(init_struct_name))
                                {
                                    already_created_feature=true;
                                    if_present_first_index=i;
                                }
                            }
//        System.out.println(ctx.struct_multinoun().getText());
                            if(already_created_feature)
                            {
                                StringTokenizer st = new StringTokenizer(sys_features[if_present_first_index]," ");
                                String port_name=st.nextToken();
                                String valid_port_name="";
                                boolean has_found_valid_name=false;
//            System.out.println("haha");
                                while(st.hasMoreTokens())
                                {
                                    port_name=st.nextToken();
                                    if(port_name.equals(ctx.multi_flow(0).getText()+"_from_"+ctx.Struct_noun(0).toString()))
                                    {   has_found_valid_name=true;
                                        int to_add_at_last=1;
                                        while(port_name.equals(ctx.multi_flow(0).getText()+"_from_"+ctx.Struct_noun(0).toString()+to_add_at_last))
                                        {
                                            to_add_at_last+=1;
                                        }
                                        valid_port_name= ctx.multi_flow(0).getText()+"_from_"+ctx.Struct_noun(0).toString()+to_add_at_last;
                                    }
                                }
//            System.out.println("hehe");
                                if(has_found_valid_name)
                                {
                                    sys_features[if_present_first_index]=sys_features[if_present_first_index]+" "+valid_port_name;
                                }
                                else {
                                    sys_features[if_present_first_index]=sys_features[if_present_first_index]+" "+ctx.multi_flow(0).getText()+"_from_"+ctx.Struct_noun(0).toString();
                                }
                                StringTokenizer get_last_port=new StringTokenizer(sys_features[if_present_first_index]," ");
                                String name_of_last_port="";
                                while(get_last_port.hasMoreTokens())
                                {
                                    name_of_last_port=get_last_port.nextToken();
                                }
//            System.out.println(name_of_last_port);
                                String new_decl="";
                                int second_line=1;
                                for(int i=0;i<=index_names-1;i++) {
                                    if (name_of_comp.equals(system_names[i])) {
//                    System.out.println(ctx.struct_multinoun().getText());
                                        for (int j = 0; j <= system_declaration[i][0].length() - 1; j++) {
                                            if (system_declaration[i][0].charAt(j) == '\n' && second_line == 0) {
                                                new_decl=new_decl+"\n\t\t"+name_of_last_port+" : in data port;\n";
                                                second_line-=1;
                                            }
                                            else {
                                                new_decl=new_decl+system_declaration[i][0].charAt(j);
                                                if(system_declaration[i][0].charAt(j) == '\n')
                                                {
                                                    second_line-=1;
                                                }
                                            }

                                        }
                                        system_declaration[i][0]=new_decl;
                                    }
                                }
                            }
                            else {
                                sys_features[index_features] = name_of_comp;
                                String new_decl = "";
                                sys_features[index_features] = sys_features[index_features] + " " + ctx.multi_flow(0).getText() + "_from_" + ctx.Struct_noun(0).toString();
                                index_features += 1;
                                int first_new_line = 0;
                                for (int i = 0; i <= index_names - 1; i++) {
                                    if (name_of_comp.equals(system_names[i])) {
                                        for (int j = 0; j <= system_declaration[i][0].length() - 1; j++) {
                                            if (system_declaration[i][0].charAt(j) == '\n' && first_new_line == 0) {
                                                new_decl = new_decl + "\n\tfeatures\n";
                                                new_decl = new_decl + "\t\t" + ctx.multi_flow(0).getText() + "_from_" + ctx.Struct_noun(0).toString() + " : in data port;\n";
                                                first_new_line += 1;
                                            } else {
                                                new_decl = new_decl + system_declaration[i][0].charAt(j);
                                            }

                                        }
                                        system_declaration[i][0] = new_decl;
                                    }
                                }
                            }

                            copy_of_second-=1;
                            if(copy_of_second==1)
                            {
                                break;
                            }

                        }
                    }

                }

                get_updated_decl(ctx, under_same_component, path_to_first, path_to_second);

            }
        }
        else if(func_verb_of_stmt.equals("transfers"))
        {
            if(flow_has_energy && !ctx.struct_multinoun().getText().contains(",") && !ctx.struct_multinoun().getText().contains("and"))
            {
                String path_to_first=ctx.Struct_noun(0).toString();
                String path_to_second="";
                boolean under_same_component=false;
                for(int i=0;i<=index_subcomponents-1;i++)
                {
                    if(system_subcomponents[i].contains(ctx.Struct_noun(0).toString()) && system_subcomponents[i].contains(ctx.struct_multinoun().Struct_noun(0).getText()))
                    {
                        under_same_component=true;
                        path_to_second=ctx.struct_multinoun().Struct_noun(0).getText();
//                                   //System.out.println("Under same component");
                    }

                }
                if(!under_same_component) {
                    int index_of_first_comp_upper = -1;
                    boolean found_it = false;
                    int index_of_second_comp_upper = -1;
                    for (int i = 0; i <= index_subcomponents - 1; i++) {
                        if (system_subcomponents[i].contains(ctx.struct_multinoun().Struct_noun(0).getText())) {
                            index_of_second_comp_upper = i;
                        }
                        if (system_subcomponents[i].contains(ctx.Struct_noun(0).toString())) {
                            index_of_first_comp_upper = i;
                        }
                    }

                    int copy_of_second_comp = index_of_second_comp_upper;
                    String second = ctx.struct_multinoun().Struct_noun(0).getText();
                    while (index_of_first_comp_upper >= 0) {
                        StringTokenizer st = new StringTokenizer(system_subcomponents[index_of_first_comp_upper], " ");
                        String first = st.nextToken();
                        //System.out.println(path_to_first.contains(first));
                        //System.out.println(path_to_first);
                        if(first.equals(path_to_first))
                        {

                        }
                        else {
                            path_to_first = first + "-" + path_to_first;
                        }
                        second = ctx.struct_multinoun().Struct_noun(0).getText();
                        path_to_second = second;
                        for (int i = 0; i <= index_subcomponents - 1; i++) {
                            if (system_subcomponents[i].contains(ctx.struct_multinoun().Struct_noun(0).getText())) {
                                index_of_second_comp_upper = i;
                            }
                        }
                        while (index_of_second_comp_upper >= 0) {
                            //System.out.println(first);
                            for (int i = 0; i <= index_subcomponents - 1; i++) {
                                if (system_subcomponents[i].contains(first) && system_subcomponents[i].contains(second)) {
                                    StringTokenizer st3 = new StringTokenizer(system_subcomponents[i], " ");
                                    String common_comp_for_conn = st3.nextToken();
                                    if(path_to_first.contains(common_comp_for_conn))
                                    {

                                    }
                                    else {
                                        path_to_first = common_comp_for_conn + "-" + path_to_first;
                                    }
                                    if(path_to_second.contains(common_comp_for_conn))
                                    {

                                    }
                                    else {
                                        path_to_second = common_comp_for_conn + "-" + path_to_second;
                                    }
                                    found_it = true;
                                    break;
                                }
                            }
                            if (found_it) {
                                break;
                            } else {
                                if (index_of_second_comp_upper == 0) {
                                    break;
                                }
                                for (int j = 0; j <= index_of_second_comp_upper; j++) {
                                    boolean val=false;
                                    if (system_subcomponents[j].contains(second)) {
                                        //System.out.println("Outer second");
                                        index_of_second_comp_upper = j;
                                        StringTokenizer st2 = new StringTokenizer(system_subcomponents[index_of_second_comp_upper], " ");
                                        String new_second = st2.nextToken();
                                        if(new_second.equals(second))
                                        {
                                            for (int k = 0; k <= index_of_second_comp_upper-1; k++) {
                                                //System.out.println("Inner second");
                                                if (system_subcomponents[k].contains(second)) {
                                                    index_of_second_comp_upper = k;
                                                    st2 = new StringTokenizer(system_subcomponents[index_of_second_comp_upper], " ");
                                                    second = st2.nextToken();
                                                    path_to_second = second + "-" + path_to_second;
                                                    val=true;
                                                    break;
                                                }
                                            }
                                        }
                                        else {
                                            second=new_second;
                                            path_to_second = second + "-" + path_to_second;
                                            val=true;
                                            break;
                                        }
                                        if(val)
                                        {
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                        if (found_it) {
                            break;
                        } else {
                            if (index_of_first_comp_upper == 0) {
                                break;
                            }
                            for (int j = 0; j <= index_of_first_comp_upper; j++) {
                                //System.out.println("Outer first");
                                boolean val=false;
                                if (system_subcomponents[j].contains(first)) {
                                    index_of_first_comp_upper = j;
                                    StringTokenizer st2 = new StringTokenizer(system_subcomponents[index_of_first_comp_upper], " ");
                                    String new_first = st2.nextToken();
                                    if(first.equals(new_first)) {
                                        for (int k = 0; k <= index_of_first_comp_upper-1; k++) {
                                            //System.out.println("Inner first");
                                            if (system_subcomponents[k].contains(first)) {
                                                index_of_first_comp_upper = k;
                                                val=true;
                                                break;
                                            }
                                        }
                                    }
                                    else{
                                        val=true;
                                        break;
                                    }
                                    if(val)
                                    {
                                        break;
                                    }
                                }
                            }
                        }
                    }

                }


                boolean already_created_feature=false;
                int if_present_first_index=-1;
                for(int i=0;i<=index_features-1;i++)
                {
                    StringTokenizer st = new StringTokenizer(sys_features[i]," ");
                    String comp_name=st.nextToken();
                    String init_struct_name=ctx.Struct_noun(0).toString();
                    if(comp_name.equals(init_struct_name))
                    {
                        already_created_feature=true;
                        if_present_first_index=i;
                    }
                }

                if(already_created_feature)
                {
                    StringTokenizer st = new StringTokenizer(sys_features[if_present_first_index]," ");
                    String port_name=st.nextToken();
                    String valid_port_name="";
                    boolean has_found_valid_name=false;
                    while(st.hasMoreTokens())
                    {
                        port_name=st.nextToken();
                        if(port_name.equals(ctx.multi_flow(0).getText()+"_to_"+ctx.struct_multinoun().getText()))
                        {
                            int to_add_at_last=1;
                            while(port_name.equals(ctx.multi_flow(0).getText()+"_to_"+ctx.struct_multinoun().getText()+to_add_at_last))
                            {
                                to_add_at_last+=1;
                            }
                            valid_port_name= ctx.multi_flow(0).getText()+"_to_"+ctx.struct_multinoun().getText()+to_add_at_last;
                            has_found_valid_name=true;
                        }
                    }
                    if(has_found_valid_name)
                    {
                        sys_features[if_present_first_index]=sys_features[if_present_first_index]+" "+valid_port_name;
                    }
                    else {
                        sys_features[if_present_first_index]=sys_features[if_present_first_index]+" "+ctx.multi_flow(0).getText()+"_to_"+ctx.struct_multinoun().getText();
                    }
                    StringTokenizer get_last_port=new StringTokenizer(sys_features[if_present_first_index]," ");
                    String name_of_last_port="";
                    while(get_last_port.hasMoreTokens())
                    {
                        name_of_last_port=get_last_port.nextToken();
                    }
                    String new_decl="";
                    int second_line=1;
                    for(int i=0;i<=index_names-1;i++) {
                        if (ctx.Struct_noun(0).toString().equals(system_names[i])) {
                            for (int j = 0; j <= system_declaration[i][0].length() - 1; j++) {
                                if (system_declaration[i][0].charAt(j) == '\n' && second_line == 0) {
                                    new_decl=new_decl+"\n\t\t"+name_of_last_port+" : out data port;\n";
                                    second_line-=1;
                                }
                                else {
                                    new_decl=new_decl+system_declaration[i][0].charAt(j);
                                    if(system_declaration[i][0].charAt(j) == '\n')
                                    {
                                        second_line-=1;
                                    }
                                }

                            }
                            system_declaration[i][0]=new_decl;
                        }
                    }
                }
                else {
                    sys_features[index_features]=ctx.Struct_noun(0).toString();
//            System.out.println(ctx.struct_multinoun().getText());
                    String new_decl="";
                    sys_features[index_features]=sys_features[index_features]+" "+ctx.multi_flow(0).getText()+"_to_"+ctx.struct_multinoun().getText();
                    index_features+=1;
//            System.out.println("Increased");
                    int first_new_line=0;
                    for(int i=0;i<=index_names-1;i++) {
                        if (ctx.Struct_noun(0).toString().equals(system_names[i])) {
                            for (int j = 0; j <= system_declaration[i][0].length() - 1; j++) {
                                if (system_declaration[i][0].charAt(j) == '\n' && first_new_line == 0) {
                                    new_decl = new_decl + "\n\tfeatures\n";
                                    new_decl=new_decl+"\t\t"+ctx.multi_flow(0).getText()+"_to_"+ctx.struct_multinoun().getText()+" : out data port;\n";
                                    first_new_line+=1;
                                }
                                else {
                                    new_decl=new_decl+system_declaration[i][0].charAt(j);
                                }

                            }
                            system_declaration[i][0]=new_decl;
                        }
                    }
                }



                // For thr input port of the end comp
//        System.out.println("Start of second end");
                already_created_feature=false;
                if_present_first_index=-1;
                for(int i=0;i<=index_features-1;i++)
                {
                    StringTokenizer st = new StringTokenizer(sys_features[i]," ");
                    String comp_name=st.nextToken();
                    String init_struct_name=ctx.struct_multinoun().getText();
                    if(comp_name.equals(init_struct_name))
                    {
                        already_created_feature=true;
                        if_present_first_index=i;
                    }
                }
//        System.out.println(ctx.struct_multinoun().getText());
                if(already_created_feature)
                {
                    StringTokenizer st = new StringTokenizer(sys_features[if_present_first_index]," ");
                    String port_name=st.nextToken();
                    String valid_port_name="";
                    boolean has_found_valid_name=false;
//            System.out.println("haha");
                    while(st.hasMoreTokens())
                    {
                        port_name=st.nextToken();
                        if(port_name.equals(ctx.multi_flow(0).getText()+"_from_"+ctx.Struct_noun(0).toString()))
                        {   has_found_valid_name=true;
                            int to_add_at_last=1;
                            while(port_name.equals(ctx.multi_flow(0).getText()+"_from_"+ctx.Struct_noun(0).toString()+to_add_at_last))
                            {
                                to_add_at_last+=1;
                            }
                            valid_port_name= ctx.multi_flow(0).getText()+"_from_"+ctx.Struct_noun(0).toString()+to_add_at_last;
                        }
                    }
//            System.out.println("hehe");
                    if(has_found_valid_name)
                    {
                        sys_features[if_present_first_index]=sys_features[if_present_first_index]+" "+valid_port_name;
                    }
                    else {
                        sys_features[if_present_first_index]=sys_features[if_present_first_index]+" "+ctx.multi_flow(0).getText()+"_from_"+ctx.Struct_noun(0).toString();
                    }
                    StringTokenizer get_last_port=new StringTokenizer(sys_features[if_present_first_index]," ");
                    String name_of_last_port="";
                    while(get_last_port.hasMoreTokens())
                    {
                        name_of_last_port=get_last_port.nextToken();
                    }
//            System.out.println(name_of_last_port);
                    String new_decl="";
                    int second_line=1;
                    for(int i=0;i<=index_names-1;i++) {
                        if (ctx.struct_multinoun().getText().equals(system_names[i])) {
//                    System.out.println(ctx.struct_multinoun().getText());
                            for (int j = 0; j <= system_declaration[i][0].length() - 1; j++) {
                                if (system_declaration[i][0].charAt(j) == '\n' && second_line == 0) {
                                    new_decl=new_decl+"\n\t\t"+name_of_last_port+" : in data port;\n";
                                    second_line-=1;
                                }
                                else {
                                    new_decl=new_decl+system_declaration[i][0].charAt(j);
                                    if(system_declaration[i][0].charAt(j) == '\n')
                                    {
                                        second_line-=1;
                                    }
                                }

                            }
                            system_declaration[i][0]=new_decl;
                        }
                    }
                }
                else {
                    sys_features[index_features]=ctx.struct_multinoun().getText();
                    String new_decl="";
                    sys_features[index_features]=sys_features[index_features]+" "+ctx.multi_flow(0).getText()+"_from_"+ctx.Struct_noun(0).toString();
                    index_features+=1;
                    int first_new_line=0;
                    for(int i=0;i<=index_names-1;i++) {
                        if (ctx.struct_multinoun().getText().equals(system_names[i])) {
                            for (int j = 0; j <= system_declaration[i][0].length() - 1; j++) {
                                if (system_declaration[i][0].charAt(j) == '\n' && first_new_line == 0) {
                                    new_decl = new_decl + "\n\tfeatures\n";
                                    new_decl=new_decl+"\t\t"+ctx.multi_flow(0).getText()+"_from_"+ctx.Struct_noun(0).toString()+" : in data port;\n";
                                    first_new_line+=1;
                                }
                                else {
                                    new_decl=new_decl+system_declaration[i][0].charAt(j);
                                }

                            }
                            system_declaration[i][0]=new_decl;
                        }
                    }
                }

                StringTokenizer split_first= new StringTokenizer(path_to_first,"-");
                int number_of_int_first=0;
                while(split_first.hasMoreTokens())
                {
                    String inter_sys=split_first.nextToken();
                    number_of_int_first+=1;
                }

                StringTokenizer split_second= new StringTokenizer(path_to_second,"-");
                int number_of_int_second=0;
                while(split_second.hasMoreTokens())
                {
                    String inter_sys=split_second.nextToken();
                    number_of_int_second+=1;
                }

                int copy_of_first=number_of_int_first;
                int copy_of_second=number_of_int_second;

                split_first= new StringTokenizer(path_to_first,"-");
                split_second=new StringTokenizer(path_to_second,"-");

                //System.out.println(path_to_first);
                //System.out.println(path_to_second);
                //System.out.println(number_of_int_first);
                //System.out.println(number_of_int_second);

                if(number_of_int_first>=3)
                {
                    while(copy_of_first>1)
                    {
                        if(copy_of_first==number_of_int_first)
                        {
                            split_first.nextToken();
                            copy_of_first-=1;
                        }
                        else
                        {
                            String name_of_comp=split_first.nextToken();
                            already_created_feature=false;
                            if_present_first_index=-1;
                            for(int i=0;i<=index_features-1;i++)
                            {
                                StringTokenizer st = new StringTokenizer(sys_features[i]," ");
                                String comp_name=st.nextToken();
                                String init_struct_name=name_of_comp;
                                if(comp_name.equals(init_struct_name))
                                {
                                    already_created_feature=true;
                                    if_present_first_index=i;
                                }
                            }

                            if(already_created_feature)
                            {
                                StringTokenizer st = new StringTokenizer(sys_features[if_present_first_index]," ");
                                String port_name=st.nextToken();
                                String valid_port_name="";
                                boolean has_found_valid_name=false;
                                while(st.hasMoreTokens())
                                {
                                    port_name=st.nextToken();
                                    if(port_name.equals(ctx.multi_flow(0).getText()+"_to_"+ctx.struct_multinoun().getText()))
                                    {
                                        int to_add_at_last=1;
                                        while(port_name.equals(ctx.multi_flow(0).getText()+"_to_"+ctx.struct_multinoun().getText()+to_add_at_last))
                                        {
                                            to_add_at_last+=1;
                                        }
                                        valid_port_name= ctx.multi_flow(0).getText()+"_to_"+ctx.struct_multinoun().getText()+to_add_at_last;
                                        has_found_valid_name=true;
                                    }
                                }
                                if(has_found_valid_name)
                                {
                                    sys_features[if_present_first_index]=sys_features[if_present_first_index]+" "+valid_port_name;
                                }
                                else {
                                    sys_features[if_present_first_index]=sys_features[if_present_first_index]+" "+ctx.multi_flow(0).getText()+"_to_"+ctx.struct_multinoun().getText();
                                }
                                StringTokenizer get_last_port=new StringTokenizer(sys_features[if_present_first_index]," ");
                                String name_of_last_port="";
                                while(get_last_port.hasMoreTokens())
                                {
                                    name_of_last_port=get_last_port.nextToken();
                                }
                                String new_decl="";
                                int second_line=1;
                                for(int i=0;i<=index_names-1;i++) {
                                    if (name_of_comp.equals(system_names[i])) {
                                        for (int j = 0; j <= system_declaration[i][0].length() - 1; j++) {
                                            if (system_declaration[i][0].charAt(j) == '\n' && second_line == 0) {
                                                new_decl=new_decl+"\n\t\t"+name_of_last_port+" : out data port;\n";
                                                second_line-=1;
                                            }
                                            else {
                                                new_decl=new_decl+system_declaration[i][0].charAt(j);
                                                if(system_declaration[i][0].charAt(j) == '\n')
                                                {
                                                    second_line-=1;
                                                }
                                            }

                                        }
                                        system_declaration[i][0]=new_decl;
                                    }
                                }
                            }
                            else {
                                sys_features[index_features]=name_of_comp;
//            System.out.println(ctx.struct_multinoun().getText());
                                String new_decl="";
                                sys_features[index_features]=sys_features[index_features]+" "+ctx.multi_flow(0).getText()+"_to_"+ctx.struct_multinoun().getText();
                                index_features+=1;
//            System.out.println("Increased");
                                int first_new_line=0;
                                for(int i=0;i<=index_names-1;i++) {
                                    if (name_of_comp.equals(system_names[i])) {
                                        for (int j = 0; j <= system_declaration[i][0].length() - 1; j++) {
                                            if (system_declaration[i][0].charAt(j) == '\n' && first_new_line == 0) {
                                                new_decl = new_decl + "\n\tfeatures\n";
                                                new_decl=new_decl+"\t\t"+ctx.multi_flow(0).getText()+"_to_"+ctx.struct_multinoun().getText()+" : out data port;\n";
                                                first_new_line+=1;
                                            }
                                            else {
                                                new_decl=new_decl+system_declaration[i][0].charAt(j);
                                            }

                                        }
                                        system_declaration[i][0]=new_decl;
                                    }
                                }
                            }



                            // For thr input port of the end comp
//        System.out.println("Start of second end");
                            already_created_feature=false;
                            if_present_first_index=-1;
                            for(int i=0;i<=index_features-1;i++)
                            {
                                StringTokenizer st = new StringTokenizer(sys_features[i]," ");
                                String comp_name=st.nextToken();
                                String init_struct_name=name_of_comp;
                                if(comp_name.equals(init_struct_name))
                                {
                                    already_created_feature=true;
                                    if_present_first_index=i;
                                }
                            }
//        System.out.println(ctx.struct_multinoun().getText());
                            if(already_created_feature)
                            {
                                StringTokenizer st = new StringTokenizer(sys_features[if_present_first_index]," ");
                                String port_name=st.nextToken();
                                String valid_port_name="";
                                boolean has_found_valid_name=false;
//            System.out.println("haha");
                                while(st.hasMoreTokens())
                                {
                                    port_name=st.nextToken();
                                    if(port_name.equals(ctx.multi_flow(0).getText()+"_from_"+ctx.Struct_noun(0).toString()))
                                    {   has_found_valid_name=true;
                                        int to_add_at_last=1;
                                        while(port_name.equals(ctx.multi_flow(0).getText()+"_from_"+ctx.Struct_noun(0).toString()+to_add_at_last))
                                        {
                                            to_add_at_last+=1;
                                        }
                                        valid_port_name= ctx.multi_flow(0).getText()+"_from_"+ctx.Struct_noun(0).toString()+to_add_at_last;
                                    }
                                }
//            System.out.println("hehe");
                                if(has_found_valid_name)
                                {
                                    sys_features[if_present_first_index]=sys_features[if_present_first_index]+" "+valid_port_name;
                                }
                                else {
                                    sys_features[if_present_first_index]=sys_features[if_present_first_index]+" "+ctx.multi_flow(0).getText()+"_from_"+ctx.Struct_noun(0).toString();
                                }
                                StringTokenizer get_last_port=new StringTokenizer(sys_features[if_present_first_index]," ");
                                String name_of_last_port="";
                                while(get_last_port.hasMoreTokens())
                                {
                                    name_of_last_port=get_last_port.nextToken();
                                }
//            System.out.println(name_of_last_port);
                                String new_decl="";
                                int second_line=1;
                                for(int i=0;i<=index_names-1;i++) {
                                    if (name_of_comp.equals(system_names[i])) {
//                    System.out.println(ctx.struct_multinoun().getText());
                                        for (int j = 0; j <= system_declaration[i][0].length() - 1; j++) {
                                            if (system_declaration[i][0].charAt(j) == '\n' && second_line == 0) {
                                                new_decl=new_decl+"\n\t\t"+name_of_last_port+" : in data port;\n";
                                                second_line-=1;
                                            }
                                            else {
                                                new_decl=new_decl+system_declaration[i][0].charAt(j);
                                                if(system_declaration[i][0].charAt(j) == '\n')
                                                {
                                                    second_line-=1;
                                                }
                                            }

                                        }
                                        system_declaration[i][0]=new_decl;
                                    }
                                }
                            }
                            else {
                                sys_features[index_features] = name_of_comp;
                                String new_decl = "";
                                sys_features[index_features] = sys_features[index_features] + " " + ctx.multi_flow(0).getText() + "_from_" + ctx.Struct_noun(0).toString();
                                index_features += 1;
                                int first_new_line = 0;
                                for (int i = 0; i <= index_names - 1; i++) {
                                    if (name_of_comp.equals(system_names[i])) {
                                        for (int j = 0; j <= system_declaration[i][0].length() - 1; j++) {
                                            if (system_declaration[i][0].charAt(j) == '\n' && first_new_line == 0) {
                                                new_decl = new_decl + "\n\tfeatures\n";
                                                new_decl = new_decl + "\t\t" + ctx.multi_flow(0).getText() + "_from_" + ctx.Struct_noun(0).toString() + " : in data port;\n";
                                                first_new_line += 1;
                                            } else {
                                                new_decl = new_decl + system_declaration[i][0].charAt(j);
                                            }

                                        }
                                        system_declaration[i][0] = new_decl;
                                    }
                                }
                            }

                            copy_of_first-=1;
                            if(copy_of_first==1)
                            {
                                break;
                            }

                        }
                    }
                }

                if(number_of_int_second>=3)
                {
                    while(copy_of_second>1)
                    {
                        if(copy_of_second==number_of_int_second)
                        {
                            split_second.nextToken();
                            copy_of_second-=1;
                            //System.out.println("Skip first");
                        }
                        else
                        {
                            String name_of_comp=split_second.nextToken();
                            //System.out.println(name_of_comp);
                            already_created_feature=false;
                            if_present_first_index=-1;
                            for(int i=0;i<=index_features-1;i++)
                            {
                                StringTokenizer st = new StringTokenizer(sys_features[i]," ");
                                String comp_name=st.nextToken();
                                String init_struct_name=name_of_comp;
                                if(comp_name.equals(init_struct_name))
                                {
                                    already_created_feature=true;
                                    if_present_first_index=i;
                                }
                            }

                            if(already_created_feature)
                            {
                                StringTokenizer st = new StringTokenizer(sys_features[if_present_first_index]," ");
                                String port_name=st.nextToken();
                                String valid_port_name="";
                                boolean has_found_valid_name=false;
                                while(st.hasMoreTokens())
                                {
                                    port_name=st.nextToken();
                                    if(port_name.equals(ctx.multi_flow(0).getText()+"_to_"+ctx.struct_multinoun().getText()))
                                    {
                                        int to_add_at_last=1;
                                        while(port_name.equals(ctx.multi_flow(0).getText()+"_to_"+ctx.struct_multinoun().getText()+to_add_at_last))
                                        {
                                            to_add_at_last+=1;
                                        }
                                        valid_port_name= ctx.multi_flow(0).getText()+"_to_"+ctx.struct_multinoun().getText()+to_add_at_last;
                                        has_found_valid_name=true;
                                    }
                                }
                                if(has_found_valid_name)
                                {
                                    sys_features[if_present_first_index]=sys_features[if_present_first_index]+" "+valid_port_name;
                                }
                                else {
                                    sys_features[if_present_first_index]=sys_features[if_present_first_index]+" "+ctx.multi_flow(0).getText()+"_to_"+ctx.struct_multinoun().getText();
                                }
                                StringTokenizer get_last_port=new StringTokenizer(sys_features[if_present_first_index]," ");
                                String name_of_last_port="";
                                while(get_last_port.hasMoreTokens())
                                {
                                    name_of_last_port=get_last_port.nextToken();
                                }
                                String new_decl="";
                                int second_line=1;
                                for(int i=0;i<=index_names-1;i++) {
                                    if (name_of_comp.equals(system_names[i])) {
                                        for (int j = 0; j <= system_declaration[i][0].length() - 1; j++) {
                                            if (system_declaration[i][0].charAt(j) == '\n' && second_line == 0) {
                                                new_decl=new_decl+"\n\t\t"+name_of_last_port+" : out data port;\n";
                                                second_line-=1;
                                            }
                                            else {
                                                new_decl=new_decl+system_declaration[i][0].charAt(j);
                                                if(system_declaration[i][0].charAt(j) == '\n')
                                                {
                                                    second_line-=1;
                                                }
                                            }

                                        }
                                        system_declaration[i][0]=new_decl;
                                    }
                                }
                            }
                            else {
                                sys_features[index_features]=name_of_comp;
//            System.out.println(ctx.struct_multinoun().getText());
                                String new_decl="";
                                sys_features[index_features]=sys_features[index_features]+" "+ctx.multi_flow(0).getText()+"_to_"+ctx.struct_multinoun().getText();
                                index_features+=1;
//            System.out.println("Increased");
                                int first_new_line=0;
                                for(int i=0;i<=index_names-1;i++) {
                                    if (name_of_comp.equals(system_names[i])) {
                                        for (int j = 0; j <= system_declaration[i][0].length() - 1; j++) {
                                            if (system_declaration[i][0].charAt(j) == '\n' && first_new_line == 0) {
                                                new_decl = new_decl + "\n\tfeatures\n";
                                                new_decl=new_decl+"\t\t"+ctx.multi_flow(0).getText()+"_to_"+ctx.struct_multinoun().getText()+" : out data port;\n";
                                                first_new_line+=1;
                                            }
                                            else {
                                                new_decl=new_decl+system_declaration[i][0].charAt(j);
                                            }

                                        }
                                        system_declaration[i][0]=new_decl;
                                    }
                                }
                            }



                            // For thr input port of the end comp
//        System.out.println("Start of second end");
                            already_created_feature=false;
                            if_present_first_index=-1;
                            for(int i=0;i<=index_features-1;i++)
                            {
                                StringTokenizer st = new StringTokenizer(sys_features[i]," ");
                                String comp_name=st.nextToken();
                                String init_struct_name=name_of_comp;
                                if(comp_name.equals(init_struct_name))
                                {
                                    already_created_feature=true;
                                    if_present_first_index=i;
                                }
                            }
//        System.out.println(ctx.struct_multinoun().getText());
                            if(already_created_feature)
                            {
                                StringTokenizer st = new StringTokenizer(sys_features[if_present_first_index]," ");
                                String port_name=st.nextToken();
                                String valid_port_name="";
                                boolean has_found_valid_name=false;
//            System.out.println("haha");
                                while(st.hasMoreTokens())
                                {
                                    port_name=st.nextToken();
                                    if(port_name.equals(ctx.multi_flow(0).getText()+"_from_"+ctx.Struct_noun(0).toString()))
                                    {   has_found_valid_name=true;
                                        int to_add_at_last=1;
                                        while(port_name.equals(ctx.multi_flow(0).getText()+"_from_"+ctx.Struct_noun(0).toString()+to_add_at_last))
                                        {
                                            to_add_at_last+=1;
                                        }
                                        valid_port_name= ctx.multi_flow(0).getText()+"_from_"+ctx.Struct_noun(0).toString()+to_add_at_last;
                                    }
                                }
//            System.out.println("hehe");
                                if(has_found_valid_name)
                                {
                                    sys_features[if_present_first_index]=sys_features[if_present_first_index]+" "+valid_port_name;
                                }
                                else {
                                    sys_features[if_present_first_index]=sys_features[if_present_first_index]+" "+ctx.multi_flow(0).getText()+"_from_"+ctx.Struct_noun(0).toString();
                                }
                                StringTokenizer get_last_port=new StringTokenizer(sys_features[if_present_first_index]," ");
                                String name_of_last_port="";
                                while(get_last_port.hasMoreTokens())
                                {
                                    name_of_last_port=get_last_port.nextToken();
                                }
//            System.out.println(name_of_last_port);
                                String new_decl="";
                                int second_line=1;
                                for(int i=0;i<=index_names-1;i++) {
                                    if (name_of_comp.equals(system_names[i])) {
//                    System.out.println(ctx.struct_multinoun().getText());
                                        for (int j = 0; j <= system_declaration[i][0].length() - 1; j++) {
                                            if (system_declaration[i][0].charAt(j) == '\n' && second_line == 0) {
                                                new_decl=new_decl+"\n\t\t"+name_of_last_port+" : in data port;\n";
                                                second_line-=1;
                                            }
                                            else {
                                                new_decl=new_decl+system_declaration[i][0].charAt(j);
                                                if(system_declaration[i][0].charAt(j) == '\n')
                                                {
                                                    second_line-=1;
                                                }
                                            }

                                        }
                                        system_declaration[i][0]=new_decl;
                                    }
                                }
                            }
                            else {
                                sys_features[index_features] = name_of_comp;
                                String new_decl = "";
                                sys_features[index_features] = sys_features[index_features] + " " + ctx.multi_flow(0).getText() + "_from_" + ctx.Struct_noun(0).toString();
                                index_features += 1;
                                int first_new_line = 0;
                                for (int i = 0; i <= index_names - 1; i++) {
                                    if (name_of_comp.equals(system_names[i])) {
                                        for (int j = 0; j <= system_declaration[i][0].length() - 1; j++) {
                                            if (system_declaration[i][0].charAt(j) == '\n' && first_new_line == 0) {
                                                new_decl = new_decl + "\n\tfeatures\n";
                                                new_decl = new_decl + "\t\t" + ctx.multi_flow(0).getText() + "_from_" + ctx.Struct_noun(0).toString() + " : in data port;\n";
                                                first_new_line += 1;
                                            } else {
                                                new_decl = new_decl + system_declaration[i][0].charAt(j);
                                            }

                                        }
                                        system_declaration[i][0] = new_decl;
                                    }
                                }
                            }

                            copy_of_second-=1;
                            if(copy_of_second==1)
                            {
                                break;
                            }

                        }
                    }

                }

                get_updated_decl(ctx, under_same_component, path_to_first, path_to_second);
            }
        }
        else if(func_verb_of_stmt.equals("guides"))
        {
            if(!flow_has_energy && !ctx.struct_multinoun().getText().contains(",") && !ctx.struct_multinoun().getText().contains("and"))
            {
                String path_to_first=ctx.Struct_noun(0).toString();
                String path_to_second="";
                boolean under_same_component=false;
                for(int i=0;i<=index_subcomponents-1;i++)
                {
                    if(system_subcomponents[i].contains(ctx.Struct_noun(0).toString()) && system_subcomponents[i].contains(ctx.struct_multinoun().Struct_noun(0).getText()))
                    {
                        under_same_component=true;
                        path_to_second=ctx.struct_multinoun().Struct_noun(0).getText();
//                                   //System.out.println("Under same component");
                    }

                }
                if(!under_same_component) {
                    int index_of_first_comp_upper = -1;
                    boolean found_it = false;
                    int index_of_second_comp_upper = -1;
                    for (int i = 0; i <= index_subcomponents - 1; i++) {
                        if (system_subcomponents[i].contains(ctx.struct_multinoun().Struct_noun(0).getText())) {
                            index_of_second_comp_upper = i;
                        }
                        if (system_subcomponents[i].contains(ctx.Struct_noun(0).toString())) {
                            index_of_first_comp_upper = i;
                        }
                    }

                    int copy_of_second_comp = index_of_second_comp_upper;
                    String second = ctx.struct_multinoun().Struct_noun(0).getText();
                    while (index_of_first_comp_upper >= 0) {
                        StringTokenizer st = new StringTokenizer(system_subcomponents[index_of_first_comp_upper], " ");
                        String first = st.nextToken();
                        //System.out.println(path_to_first.contains(first));
                        //System.out.println(path_to_first);
                        if(first.equals(path_to_first))
                        {

                        }
                        else {
                            path_to_first = first + "-" + path_to_first;
                        }
                        second = ctx.struct_multinoun().Struct_noun(0).getText();
                        path_to_second = second;
                        for (int i = 0; i <= index_subcomponents - 1; i++) {
                            if (system_subcomponents[i].contains(ctx.struct_multinoun().Struct_noun(0).getText())) {
                                index_of_second_comp_upper = i;
                            }
                        }
                        while (index_of_second_comp_upper >= 0) {
                            //System.out.println(first);
                            //System.out.println(second);
                            for (int i = 0; i <= index_subcomponents - 1; i++) {
                                if (system_subcomponents[i].contains(first) && system_subcomponents[i].contains(second)) {
                                    StringTokenizer st3 = new StringTokenizer(system_subcomponents[i], " ");
                                    String common_comp_for_conn = st3.nextToken();

                                    if(path_to_first.contains(common_comp_for_conn))
                                    {

                                    }
                                    else {
                                        path_to_first = common_comp_for_conn + "-" + path_to_first;
                                    }
                                    if(path_to_second.contains(common_comp_for_conn))
                                    {

                                    }
                                    else {
                                        path_to_second = common_comp_for_conn + "-" + path_to_second;
                                    }
                                    found_it = true;
                                    break;
                                }
                            }
                            if (found_it) {
                                break;
                            } else {
                                if (index_of_second_comp_upper == 0) {
                                    break;
                                }
                                for (int j = 0; j <= index_of_second_comp_upper; j++) {
                                    boolean val=false;
                                    if (system_subcomponents[j].contains(second)) {
                                        //System.out.println("Outer second");
                                        index_of_second_comp_upper = j;
                                        StringTokenizer st2 = new StringTokenizer(system_subcomponents[index_of_second_comp_upper], " ");
                                        String new_second = st2.nextToken();
                                        if(new_second.equals(second))
                                        {
                                            for (int k = 0; k <= index_of_second_comp_upper-1; k++) {
                                                //System.out.println("Inner second");
                                                if (system_subcomponents[k].contains(second)) {
                                                    index_of_second_comp_upper = k;
                                                    st2 = new StringTokenizer(system_subcomponents[index_of_second_comp_upper], " ");
                                                    second = st2.nextToken();
                                                    path_to_second = second + "-" + path_to_second;
                                                    val=true;
                                                    break;
                                                }
                                            }
                                        }
                                        else {
                                            second=new_second;
                                            path_to_second = second + "-" + path_to_second;
                                            val=true;
                                            break;
                                        }
                                        if(val)
                                        {
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                        if (found_it) {
                            break;
                        } else {
                            if (index_of_first_comp_upper == 0) {
                                break;
                            }
                            for (int j = 0; j <= index_of_first_comp_upper; j++) {
                                //System.out.println("Outer first");
                                boolean val=false;
                                if (system_subcomponents[j].contains(first)) {
                                    index_of_first_comp_upper = j;
                                    StringTokenizer st2 = new StringTokenizer(system_subcomponents[index_of_first_comp_upper], " ");
                                    String new_first = st2.nextToken();
                                    if(first.equals(new_first)) {
                                        for (int k = 0; k <= index_of_first_comp_upper-1; k++) {
                                            //System.out.println("Inner first");
                                            if (system_subcomponents[k].contains(first)) {
                                                index_of_first_comp_upper = k;
                                                val=true;
                                                break;
                                            }
                                        }
                                    }
                                    else{
                                        val=true;
                                        break;
                                    }
                                    if(val)
                                    {
                                        break;
                                    }
                                }
                            }
                        }
                    }

                }

//                System.out.println(path_to_first);
//                System.out.println(path_to_second);

                boolean already_created_feature=false;
                int if_present_first_index=-1;
                for(int i=0;i<=index_features-1;i++)
                {
                    StringTokenizer st = new StringTokenizer(sys_features[i]," ");
                    String comp_name=st.nextToken();
                    String init_struct_name=ctx.Struct_noun(0).toString();
                    if(comp_name.equals(init_struct_name))
                    {
                        already_created_feature=true;
                        if_present_first_index=i;
                    }
                }

                if(already_created_feature)
                {
                    StringTokenizer st = new StringTokenizer(sys_features[if_present_first_index]," ");
                    String port_name=st.nextToken();
                    String valid_port_name="";
                    boolean has_found_valid_name=false;
                    while(st.hasMoreTokens())
                    {
                        port_name=st.nextToken();
                        if(port_name.equals(ctx.multi_flow(0).getText()+"_to_"+ctx.struct_multinoun().getText()))
                        {
                            int to_add_at_last=1;
                            while(port_name.equals(ctx.multi_flow(0).getText()+"_to_"+ctx.struct_multinoun().getText()+to_add_at_last))
                            {
                                to_add_at_last+=1;
                            }
                            valid_port_name= ctx.multi_flow(0).getText()+"_to_"+ctx.struct_multinoun().getText()+to_add_at_last;
                            has_found_valid_name=true;
                        }
                    }
                    if(has_found_valid_name)
                    {
                        sys_features[if_present_first_index]=sys_features[if_present_first_index]+" "+valid_port_name;
                    }
                    else {
                        sys_features[if_present_first_index]=sys_features[if_present_first_index]+" "+ctx.multi_flow(0).getText()+"_to_"+ctx.struct_multinoun().getText();
                    }
                    StringTokenizer get_last_port=new StringTokenizer(sys_features[if_present_first_index]," ");
                    String name_of_last_port="";
                    while(get_last_port.hasMoreTokens())
                    {
                        name_of_last_port=get_last_port.nextToken();
                    }
                    String new_decl="";
                    int second_line=1;
                    for(int i=0;i<=index_names-1;i++) {
                        if (ctx.Struct_noun(0).toString().equals(system_names[i])) {
                            for (int j = 0; j <= system_declaration[i][0].length() - 1; j++) {
                                if (system_declaration[i][0].charAt(j) == '\n' && second_line == 0) {
                                    new_decl=new_decl+"\n\t\t"+name_of_last_port+" : out data port;\n";
                                    second_line-=1;
                                }
                                else {
                                    new_decl=new_decl+system_declaration[i][0].charAt(j);
                                    if(system_declaration[i][0].charAt(j) == '\n')
                                    {
                                        second_line-=1;
                                    }
                                }

                            }
                            system_declaration[i][0]=new_decl;
                        }
                    }
                }
                else {
                    sys_features[index_features]=ctx.Struct_noun(0).toString();
//            System.out.println(ctx.struct_multinoun().getText());
                    String new_decl="";
                    sys_features[index_features]=sys_features[index_features]+" "+ctx.multi_flow(0).getText()+"_to_"+ctx.struct_multinoun().getText();
                    index_features+=1;
//            System.out.println("Increased");
                    int first_new_line=0;
                    for(int i=0;i<=index_names-1;i++) {
                        if (ctx.Struct_noun(0).toString().equals(system_names[i])) {
                            for (int j = 0; j <= system_declaration[i][0].length() - 1; j++) {
                                if (system_declaration[i][0].charAt(j) == '\n' && first_new_line == 0) {
                                    new_decl = new_decl + "\n\tfeatures\n";
                                    new_decl=new_decl+"\t\t"+ctx.multi_flow(0).getText()+"_to_"+ctx.struct_multinoun().getText()+" : out data port;\n";
                                    first_new_line+=1;
                                }
                                else {
                                    new_decl=new_decl+system_declaration[i][0].charAt(j);
                                }

                            }
                            system_declaration[i][0]=new_decl;
                        }
                    }
                }



                // For thr input port of the end comp
//        System.out.println("Start of second end");
                already_created_feature=false;
                if_present_first_index=-1;
                for(int i=0;i<=index_features-1;i++)
                {
                    StringTokenizer st = new StringTokenizer(sys_features[i]," ");
                    String comp_name=st.nextToken();
                    String init_struct_name=ctx.struct_multinoun().getText();
                    if(comp_name.equals(init_struct_name))
                    {
                        already_created_feature=true;
                        if_present_first_index=i;
                    }
                }
//        System.out.println(ctx.struct_multinoun().getText());
                if(already_created_feature)
                {
                    StringTokenizer st = new StringTokenizer(sys_features[if_present_first_index]," ");
                    String port_name=st.nextToken();
                    String valid_port_name="";
                    boolean has_found_valid_name=false;
//            System.out.println("haha");
                    while(st.hasMoreTokens())
                    {
                        port_name=st.nextToken();
                        if(port_name.equals(ctx.multi_flow(0).getText()+"_from_"+ctx.Struct_noun(0).toString()))
                        {   has_found_valid_name=true;
                            int to_add_at_last=1;
                            while(port_name.equals(ctx.multi_flow(0).getText()+"_from_"+ctx.Struct_noun(0).toString()+to_add_at_last))
                            {
                                to_add_at_last+=1;
                            }
                            valid_port_name= ctx.multi_flow(0).getText()+"_from_"+ctx.Struct_noun(0).toString()+to_add_at_last;
                        }
                    }
//            System.out.println("hehe");
                    if(has_found_valid_name)
                    {
                        sys_features[if_present_first_index]=sys_features[if_present_first_index]+" "+valid_port_name;
                    }
                    else {
                        sys_features[if_present_first_index]=sys_features[if_present_first_index]+" "+ctx.multi_flow(0).getText()+"_from_"+ctx.Struct_noun(0).toString();
                    }
                    StringTokenizer get_last_port=new StringTokenizer(sys_features[if_present_first_index]," ");
                    String name_of_last_port="";
                    while(get_last_port.hasMoreTokens())
                    {
                        name_of_last_port=get_last_port.nextToken();
                    }
//            System.out.println(name_of_last_port);
                    String new_decl="";
                    int second_line=1;
                    for(int i=0;i<=index_names-1;i++) {
                        if (ctx.struct_multinoun().getText().equals(system_names[i])) {
//                    System.out.println(ctx.struct_multinoun().getText());
                            for (int j = 0; j <= system_declaration[i][0].length() - 1; j++) {
                                if (system_declaration[i][0].charAt(j) == '\n' && second_line == 0) {
                                    new_decl=new_decl+"\n\t\t"+name_of_last_port+" : in data port;\n";
                                    second_line-=1;
                                }
                                else {
                                    new_decl=new_decl+system_declaration[i][0].charAt(j);
                                    if(system_declaration[i][0].charAt(j) == '\n')
                                    {
                                        second_line-=1;
                                    }
                                }

                            }
                            system_declaration[i][0]=new_decl;
                        }
                    }
                }
                else {
                    sys_features[index_features]=ctx.struct_multinoun().getText();
                    String new_decl="";
                    sys_features[index_features]=sys_features[index_features]+" "+ctx.multi_flow(0).getText()+"_from_"+ctx.Struct_noun(0).toString();
                    index_features+=1;
                    int first_new_line=0;
                    for(int i=0;i<=index_names-1;i++) {
                        if (ctx.struct_multinoun().getText().equals(system_names[i])) {
                            for (int j = 0; j <= system_declaration[i][0].length() - 1; j++) {
                                if (system_declaration[i][0].charAt(j) == '\n' && first_new_line == 0) {
                                    new_decl = new_decl + "\n\tfeatures\n";
                                    new_decl=new_decl+"\t\t"+ctx.multi_flow(0).getText()+"_from_"+ctx.Struct_noun(0).toString()+" : in data port;\n";
                                    first_new_line+=1;
                                }
                                else {
                                    new_decl=new_decl+system_declaration[i][0].charAt(j);
                                }

                            }
                            system_declaration[i][0]=new_decl;
                        }
                    }
                }
                StringTokenizer split_first= new StringTokenizer(path_to_first,"-");
                int number_of_int_first=0;
                while(split_first.hasMoreTokens())
                {
                    String inter_sys=split_first.nextToken();
                    number_of_int_first+=1;
                }

                StringTokenizer split_second= new StringTokenizer(path_to_second,"-");
                int number_of_int_second=0;
                while(split_second.hasMoreTokens())
                {
                    String inter_sys=split_second.nextToken();
                    number_of_int_second+=1;
                }

                int copy_of_first=number_of_int_first;
                int copy_of_second=number_of_int_second;

                split_first= new StringTokenizer(path_to_first,"-");
                split_second=new StringTokenizer(path_to_second,"-");

                //System.out.println(path_to_first);
                //System.out.println(path_to_second);
                //System.out.println(number_of_int_first);
                //System.out.println(number_of_int_second);

                if(number_of_int_first>=3)
                {
                    while(copy_of_first>1)
                    {
                        if(copy_of_first==number_of_int_first)
                        {
                            split_first.nextToken();
                            copy_of_first-=1;
                        }
                        else
                        {
                            String name_of_comp=split_first.nextToken();
                            already_created_feature=false;
                            if_present_first_index=-1;
                            for(int i=0;i<=index_features-1;i++)
                            {
                                StringTokenizer st = new StringTokenizer(sys_features[i]," ");
                                String comp_name=st.nextToken();
                                String init_struct_name=name_of_comp;
                                if(comp_name.equals(init_struct_name))
                                {
                                    already_created_feature=true;
                                    if_present_first_index=i;
                                }
                            }

                            if(already_created_feature)
                            {
                                StringTokenizer st = new StringTokenizer(sys_features[if_present_first_index]," ");
                                String port_name=st.nextToken();
                                String valid_port_name="";
                                boolean has_found_valid_name=false;
                                while(st.hasMoreTokens())
                                {
                                    port_name=st.nextToken();
                                    if(port_name.equals(ctx.multi_flow(0).getText()+"_to_"+ctx.struct_multinoun().getText()))
                                    {
                                        int to_add_at_last=1;
                                        while(port_name.equals(ctx.multi_flow(0).getText()+"_to_"+ctx.struct_multinoun().getText()+to_add_at_last))
                                        {
                                            to_add_at_last+=1;
                                        }
                                        valid_port_name= ctx.multi_flow(0).getText()+"_to_"+ctx.struct_multinoun().getText()+to_add_at_last;
                                        has_found_valid_name=true;
                                    }
                                }
                                if(has_found_valid_name)
                                {
                                    sys_features[if_present_first_index]=sys_features[if_present_first_index]+" "+valid_port_name;
                                }
                                else {
                                    sys_features[if_present_first_index]=sys_features[if_present_first_index]+" "+ctx.multi_flow(0).getText()+"_to_"+ctx.struct_multinoun().getText();
                                }
                                StringTokenizer get_last_port=new StringTokenizer(sys_features[if_present_first_index]," ");
                                String name_of_last_port="";
                                while(get_last_port.hasMoreTokens())
                                {
                                    name_of_last_port=get_last_port.nextToken();
                                }
                                String new_decl="";
                                int second_line=1;
                                for(int i=0;i<=index_names-1;i++) {
                                    if (name_of_comp.equals(system_names[i])) {
                                        for (int j = 0; j <= system_declaration[i][0].length() - 1; j++) {
                                            if (system_declaration[i][0].charAt(j) == '\n' && second_line == 0) {
                                                new_decl=new_decl+"\n\t\t"+name_of_last_port+" : out data port;\n";
                                                second_line-=1;
                                            }
                                            else {
                                                new_decl=new_decl+system_declaration[i][0].charAt(j);
                                                if(system_declaration[i][0].charAt(j) == '\n')
                                                {
                                                    second_line-=1;
                                                }
                                            }

                                        }
                                        system_declaration[i][0]=new_decl;
                                    }
                                }
                            }
                            else {
                                sys_features[index_features]=name_of_comp;
//            System.out.println(ctx.struct_multinoun().getText());
                                String new_decl="";
                                sys_features[index_features]=sys_features[index_features]+" "+ctx.multi_flow(0).getText()+"_to_"+ctx.struct_multinoun().getText();
                                index_features+=1;
//            System.out.println("Increased");
                                int first_new_line=0;
                                for(int i=0;i<=index_names-1;i++) {
                                    if (name_of_comp.equals(system_names[i])) {
                                        for (int j = 0; j <= system_declaration[i][0].length() - 1; j++) {
                                            if (system_declaration[i][0].charAt(j) == '\n' && first_new_line == 0) {
                                                new_decl = new_decl + "\n\tfeatures\n";
                                                new_decl=new_decl+"\t\t"+ctx.multi_flow(0).getText()+"_to_"+ctx.struct_multinoun().getText()+" : out data port;\n";
                                                first_new_line+=1;
                                            }
                                            else {
                                                new_decl=new_decl+system_declaration[i][0].charAt(j);
                                            }

                                        }
                                        system_declaration[i][0]=new_decl;
                                    }
                                }
                            }



                            // For thr input port of the end comp
//        System.out.println("Start of second end");
                            already_created_feature=false;
                            if_present_first_index=-1;
                            for(int i=0;i<=index_features-1;i++)
                            {
                                StringTokenizer st = new StringTokenizer(sys_features[i]," ");
                                String comp_name=st.nextToken();
                                String init_struct_name=name_of_comp;
                                if(comp_name.equals(init_struct_name))
                                {
                                    already_created_feature=true;
                                    if_present_first_index=i;
                                }
                            }
//        System.out.println(ctx.struct_multinoun().getText());
                            if(already_created_feature)
                            {
                                StringTokenizer st = new StringTokenizer(sys_features[if_present_first_index]," ");
                                String port_name=st.nextToken();
                                String valid_port_name="";
                                boolean has_found_valid_name=false;
//            System.out.println("haha");
                                while(st.hasMoreTokens())
                                {
                                    port_name=st.nextToken();
                                    if(port_name.equals(ctx.multi_flow(0).getText()+"_from_"+ctx.Struct_noun(0).toString()))
                                    {   has_found_valid_name=true;
                                        int to_add_at_last=1;
                                        while(port_name.equals(ctx.multi_flow(0).getText()+"_from_"+ctx.Struct_noun(0).toString()+to_add_at_last))
                                        {
                                            to_add_at_last+=1;
                                        }
                                        valid_port_name= ctx.multi_flow(0).getText()+"_from_"+ctx.Struct_noun(0).toString()+to_add_at_last;
                                    }
                                }
//            System.out.println("hehe");
                                if(has_found_valid_name)
                                {
                                    sys_features[if_present_first_index]=sys_features[if_present_first_index]+" "+valid_port_name;
                                }
                                else {
                                    sys_features[if_present_first_index]=sys_features[if_present_first_index]+" "+ctx.multi_flow(0).getText()+"_from_"+ctx.Struct_noun(0).toString();
                                }
                                StringTokenizer get_last_port=new StringTokenizer(sys_features[if_present_first_index]," ");
                                String name_of_last_port="";
                                while(get_last_port.hasMoreTokens())
                                {
                                    name_of_last_port=get_last_port.nextToken();
                                }
//            System.out.println(name_of_last_port);
                                String new_decl="";
                                int second_line=1;
                                for(int i=0;i<=index_names-1;i++) {
                                    if (name_of_comp.equals(system_names[i])) {
//                    System.out.println(ctx.struct_multinoun().getText());
                                        for (int j = 0; j <= system_declaration[i][0].length() - 1; j++) {
                                            if (system_declaration[i][0].charAt(j) == '\n' && second_line == 0) {
                                                new_decl=new_decl+"\n\t\t"+name_of_last_port+" : in data port;\n";
                                                second_line-=1;
                                            }
                                            else {
                                                new_decl=new_decl+system_declaration[i][0].charAt(j);
                                                if(system_declaration[i][0].charAt(j) == '\n')
                                                {
                                                    second_line-=1;
                                                }
                                            }

                                        }
                                        system_declaration[i][0]=new_decl;
                                    }
                                }
                            }
                            else {
                                sys_features[index_features] = name_of_comp;
                                String new_decl = "";
                                sys_features[index_features] = sys_features[index_features] + " " + ctx.multi_flow(0).getText() + "_from_" + ctx.Struct_noun(0).toString();
                                index_features += 1;
                                int first_new_line = 0;
                                for (int i = 0; i <= index_names - 1; i++) {
                                    if (name_of_comp.equals(system_names[i])) {
                                        for (int j = 0; j <= system_declaration[i][0].length() - 1; j++) {
                                            if (system_declaration[i][0].charAt(j) == '\n' && first_new_line == 0) {
                                                new_decl = new_decl + "\n\tfeatures\n";
                                                new_decl = new_decl + "\t\t" + ctx.multi_flow(0).getText() + "_from_" + ctx.Struct_noun(0).toString() + " : in data port;\n";
                                                first_new_line += 1;
                                            } else {
                                                new_decl = new_decl + system_declaration[i][0].charAt(j);
                                            }

                                        }
                                        system_declaration[i][0] = new_decl;
                                    }
                                }
                            }

                            copy_of_first-=1;
                            if(copy_of_first==1)
                            {
                                break;
                            }

                        }
                    }
                }

                if(number_of_int_second>=3)
                {
                    while(copy_of_second>1)
                    {
                        if(copy_of_second==number_of_int_second)
                        {
                            split_second.nextToken();
                            copy_of_second-=1;
                            //System.out.println("Skip first");
                        }
                        else
                        {
                            String name_of_comp=split_second.nextToken();
                            //System.out.println(name_of_comp);
                            already_created_feature=false;
                            if_present_first_index=-1;
                            for(int i=0;i<=index_features-1;i++)
                            {
                                StringTokenizer st = new StringTokenizer(sys_features[i]," ");
                                String comp_name=st.nextToken();
                                String init_struct_name=name_of_comp;
                                if(comp_name.equals(init_struct_name))
                                {
                                    already_created_feature=true;
                                    if_present_first_index=i;
                                }
                            }

                            if(already_created_feature)
                            {
                                StringTokenizer st = new StringTokenizer(sys_features[if_present_first_index]," ");
                                String port_name=st.nextToken();
                                String valid_port_name="";
                                boolean has_found_valid_name=false;
                                while(st.hasMoreTokens())
                                {
                                    port_name=st.nextToken();
                                    if(port_name.equals(ctx.multi_flow(0).getText()+"_to_"+ctx.struct_multinoun().getText()))
                                    {
                                        int to_add_at_last=1;
                                        while(port_name.equals(ctx.multi_flow(0).getText()+"_to_"+ctx.struct_multinoun().getText()+to_add_at_last))
                                        {
                                            to_add_at_last+=1;
                                        }
                                        valid_port_name= ctx.multi_flow(0).getText()+"_to_"+ctx.struct_multinoun().getText()+to_add_at_last;
                                        has_found_valid_name=true;
                                    }
                                }
                                if(has_found_valid_name)
                                {
                                    sys_features[if_present_first_index]=sys_features[if_present_first_index]+" "+valid_port_name;
                                }
                                else {
                                    sys_features[if_present_first_index]=sys_features[if_present_first_index]+" "+ctx.multi_flow(0).getText()+"_to_"+ctx.struct_multinoun().getText();
                                }
                                StringTokenizer get_last_port=new StringTokenizer(sys_features[if_present_first_index]," ");
                                String name_of_last_port="";
                                while(get_last_port.hasMoreTokens())
                                {
                                    name_of_last_port=get_last_port.nextToken();
                                }
                                String new_decl="";
                                int second_line=1;
                                for(int i=0;i<=index_names-1;i++) {
                                    if (name_of_comp.equals(system_names[i])) {
                                        for (int j = 0; j <= system_declaration[i][0].length() - 1; j++) {
                                            if (system_declaration[i][0].charAt(j) == '\n' && second_line == 0) {
                                                new_decl=new_decl+"\n\t\t"+name_of_last_port+" : out data port;\n";
                                                second_line-=1;
                                            }
                                            else {
                                                new_decl=new_decl+system_declaration[i][0].charAt(j);
                                                if(system_declaration[i][0].charAt(j) == '\n')
                                                {
                                                    second_line-=1;
                                                }
                                            }

                                        }
                                        system_declaration[i][0]=new_decl;
                                    }
                                }
                            }
                            else {
                                sys_features[index_features]=name_of_comp;
//            System.out.println(ctx.struct_multinoun().getText());
                                String new_decl="";
                                sys_features[index_features]=sys_features[index_features]+" "+ctx.multi_flow(0).getText()+"_to_"+ctx.struct_multinoun().getText();
                                index_features+=1;
//            System.out.println("Increased");
                                int first_new_line=0;
                                for(int i=0;i<=index_names-1;i++) {
                                    if (name_of_comp.equals(system_names[i])) {
                                        for (int j = 0; j <= system_declaration[i][0].length() - 1; j++) {
                                            if (system_declaration[i][0].charAt(j) == '\n' && first_new_line == 0) {
                                                new_decl = new_decl + "\n\tfeatures\n";
                                                new_decl=new_decl+"\t\t"+ctx.multi_flow(0).getText()+"_to_"+ctx.struct_multinoun().getText()+" : out data port;\n";
                                                first_new_line+=1;
                                            }
                                            else {
                                                new_decl=new_decl+system_declaration[i][0].charAt(j);
                                            }

                                        }
                                        system_declaration[i][0]=new_decl;
                                    }
                                }
                            }



                            // For thr input port of the end comp
//        System.out.println("Start of second end");
                            already_created_feature=false;
                            if_present_first_index=-1;
                            for(int i=0;i<=index_features-1;i++)
                            {
                                StringTokenizer st = new StringTokenizer(sys_features[i]," ");
                                String comp_name=st.nextToken();
                                String init_struct_name=name_of_comp;
                                if(comp_name.equals(init_struct_name))
                                {
                                    already_created_feature=true;
                                    if_present_first_index=i;
                                }
                            }
//        System.out.println(ctx.struct_multinoun().getText());
                            if(already_created_feature)
                            {
                                StringTokenizer st = new StringTokenizer(sys_features[if_present_first_index]," ");
                                String port_name=st.nextToken();
                                String valid_port_name="";
                                boolean has_found_valid_name=false;
//            System.out.println("haha");
                                while(st.hasMoreTokens())
                                {
                                    port_name=st.nextToken();
                                    if(port_name.equals(ctx.multi_flow(0).getText()+"_from_"+ctx.Struct_noun(0).toString()))
                                    {   has_found_valid_name=true;
                                        int to_add_at_last=1;
                                        while(port_name.equals(ctx.multi_flow(0).getText()+"_from_"+ctx.Struct_noun(0).toString()+to_add_at_last))
                                        {
                                            to_add_at_last+=1;
                                        }
                                        valid_port_name= ctx.multi_flow(0).getText()+"_from_"+ctx.Struct_noun(0).toString()+to_add_at_last;
                                    }
                                }
//            System.out.println("hehe");
                                if(has_found_valid_name)
                                {
                                    sys_features[if_present_first_index]=sys_features[if_present_first_index]+" "+valid_port_name;
                                }
                                else {
                                    sys_features[if_present_first_index]=sys_features[if_present_first_index]+" "+ctx.multi_flow(0).getText()+"_from_"+ctx.Struct_noun(0).toString();
                                }
                                StringTokenizer get_last_port=new StringTokenizer(sys_features[if_present_first_index]," ");
                                String name_of_last_port="";
                                while(get_last_port.hasMoreTokens())
                                {
                                    name_of_last_port=get_last_port.nextToken();
                                }
//            System.out.println(name_of_last_port);
                                String new_decl="";
                                int second_line=1;
                                for(int i=0;i<=index_names-1;i++) {
                                    if (name_of_comp.equals(system_names[i])) {
//                    System.out.println(ctx.struct_multinoun().getText());
                                        for (int j = 0; j <= system_declaration[i][0].length() - 1; j++) {
                                            if (system_declaration[i][0].charAt(j) == '\n' && second_line == 0) {
                                                new_decl=new_decl+"\n\t\t"+name_of_last_port+" : in data port;\n";
                                                second_line-=1;
                                            }
                                            else {
                                                new_decl=new_decl+system_declaration[i][0].charAt(j);
                                                if(system_declaration[i][0].charAt(j) == '\n')
                                                {
                                                    second_line-=1;
                                                }
                                            }

                                        }
                                        system_declaration[i][0]=new_decl;
                                    }
                                }
                            }
                            else {
                                sys_features[index_features] = name_of_comp;
                                String new_decl = "";
                                sys_features[index_features] = sys_features[index_features] + " " + ctx.multi_flow(0).getText() + "_from_" + ctx.Struct_noun(0).toString();
                                index_features += 1;
                                int first_new_line = 0;
                                for (int i = 0; i <= index_names - 1; i++) {
                                    if (name_of_comp.equals(system_names[i])) {
                                        for (int j = 0; j <= system_declaration[i][0].length() - 1; j++) {
                                            if (system_declaration[i][0].charAt(j) == '\n' && first_new_line == 0) {
                                                new_decl = new_decl + "\n\tfeatures\n";
                                                new_decl = new_decl + "\t\t" + ctx.multi_flow(0).getText() + "_from_" + ctx.Struct_noun(0).toString() + " : in data port;\n";
                                                first_new_line += 1;
                                            } else {
                                                new_decl = new_decl + system_declaration[i][0].charAt(j);
                                            }

                                        }
                                        system_declaration[i][0] = new_decl;
                                    }
                                }
                            }

                            copy_of_second-=1;
                            if(copy_of_second==1)
                            {
                                break;
                            }

                        }
                    }

                }

                get_updated_decl(ctx, under_same_component, path_to_first, path_to_second);
            }
        }
        else if(func_verb_of_stmt.equals("couples"))
        {
            if(!flow_has_energy)
            {
                //-------No ports--------------//
            }
        }
        else if(func_verb_of_stmt.equals("mixes"))
        {
            if(!flow_has_energy)
            {
                //-------No ports--------------//
            }
        }
        else if(func_verb_of_stmt.equals("regulates"))
        {
            if(!flow_has_energy)
            {
                //-------No ports--------------//
            }
        }
        else if(func_verb_of_stmt.equals("changes"))
        {
            if(!flow_has_energy)
            {
                //-------No ports--------------//
            }
        }
        else if(func_verb_of_stmt.equals("stops"))
        {
            if(!flow_has_energy)
            {
                //-------No ports--------------//
            }
        }
        else if(func_verb_of_stmt.equals("converts"))
        {
            if(flow_has_energy)
            {
                //-------No ports--------------//
            }
        }
        else if(func_verb_of_stmt.equals("stores"))
        {
            if(!flow_has_energy)
            {
                //-------No ports--------------//
            }
        }
        else if(func_verb_of_stmt.equals("supplies"))
        {
            if(!flow_has_energy && !ctx.struct_multinoun().getText().contains(",") && !ctx.struct_multinoun().getText().contains("and"))
            {
                String path_to_first=ctx.Struct_noun(0).toString();
                String path_to_second="";
                boolean under_same_component=false;
                for(int i=0;i<=index_subcomponents-1;i++)
                {
                    if(system_subcomponents[i].contains(ctx.Struct_noun(0).toString()) && system_subcomponents[i].contains(ctx.struct_multinoun().Struct_noun(0).getText()))
                    {
                        under_same_component=true;
                        path_to_second=ctx.struct_multinoun().Struct_noun(0).getText();
//                                   //System.out.println("Under same component");
                    }

                }
                if(!under_same_component) {
                    int index_of_first_comp_upper = -1;
                    boolean found_it = false;
                    int index_of_second_comp_upper = -1;
                    for (int i = 0; i <= index_subcomponents - 1; i++) {
                        if (system_subcomponents[i].contains(ctx.struct_multinoun().Struct_noun(0).getText())) {
                            index_of_second_comp_upper = i;
                        }
                        if (system_subcomponents[i].contains(ctx.Struct_noun(0).toString())) {
                            index_of_first_comp_upper = i;
                        }
                    }

                    int copy_of_second_comp = index_of_second_comp_upper;
                    String second = ctx.struct_multinoun().Struct_noun(0).getText();
                    while (index_of_first_comp_upper >= 0) {
                        StringTokenizer st = new StringTokenizer(system_subcomponents[index_of_first_comp_upper], " ");
                        String first = st.nextToken();
                        //System.out.println(path_to_first.contains(first));
                        //System.out.println(path_to_first);
                        if(first.equals(path_to_first))
                        {

                        }
                        else {
                            path_to_first = first + "-" + path_to_first;
                        }
                        second = ctx.struct_multinoun().Struct_noun(0).getText();
                        path_to_second = second;
                        for (int i = 0; i <= index_subcomponents - 1; i++) {
                            if (system_subcomponents[i].contains(ctx.struct_multinoun().Struct_noun(0).getText())) {
                                index_of_second_comp_upper = i;
                            }
                        }
                        while (index_of_second_comp_upper >= 0) {
                            //System.out.println(first);
                            //System.out.println(second);
                            for (int i = 0; i <= index_subcomponents - 1; i++) {
                                if (system_subcomponents[i].contains(first) && system_subcomponents[i].contains(second)) {
                                    StringTokenizer st3 = new StringTokenizer(system_subcomponents[i], " ");
                                    String common_comp_for_conn = st3.nextToken();

                                    if(path_to_first.contains(common_comp_for_conn))
                                    {

                                    }
                                    else {
                                        path_to_first = common_comp_for_conn + "-" + path_to_first;
                                    }
                                    if(path_to_second.contains(common_comp_for_conn))
                                    {

                                    }
                                    else {
                                        path_to_second = common_comp_for_conn + "-" + path_to_second;
                                    }
                                    found_it = true;
                                    break;
                                }
                            }
                            if (found_it) {
                                break;
                            } else {
                                if (index_of_second_comp_upper == 0) {
                                    break;
                                }
                                for (int j = 0; j <= index_of_second_comp_upper; j++) {
                                    boolean val=false;
                                    if (system_subcomponents[j].contains(second)) {
                                        //System.out.println("Outer second");
                                        index_of_second_comp_upper = j;
                                        StringTokenizer st2 = new StringTokenizer(system_subcomponents[index_of_second_comp_upper], " ");
                                        String new_second = st2.nextToken();
                                        if(new_second.equals(second))
                                        {
                                            for (int k = 0; k <= index_of_second_comp_upper-1; k++) {
                                                //System.out.println("Inner second");
                                                if (system_subcomponents[k].contains(second)) {
                                                    index_of_second_comp_upper = k;
                                                    st2 = new StringTokenizer(system_subcomponents[index_of_second_comp_upper], " ");
                                                    second = st2.nextToken();
                                                    path_to_second = second + "-" + path_to_second;
                                                    val=true;
                                                    break;
                                                }
                                            }
                                        }
                                        else {
                                            second=new_second;
                                            path_to_second = second + "-" + path_to_second;
                                            val=true;
                                            break;
                                        }
                                        if(val)
                                        {
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                        if (found_it) {
                            break;
                        } else {
                            if (index_of_first_comp_upper == 0) {
                                break;
                            }
                            for (int j = 0; j <= index_of_first_comp_upper; j++) {
                                //System.out.println("Outer first");
                                boolean val=false;
                                if (system_subcomponents[j].contains(first)) {
                                    index_of_first_comp_upper = j;
                                    StringTokenizer st2 = new StringTokenizer(system_subcomponents[index_of_first_comp_upper], " ");
                                    String new_first = st2.nextToken();
                                    if(first.equals(new_first)) {
                                        for (int k = 0; k <= index_of_first_comp_upper-1; k++) {
                                            //System.out.println("Inner first");
                                            if (system_subcomponents[k].contains(first)) {
                                                index_of_first_comp_upper = k;
                                                val=true;
                                                break;
                                            }
                                        }
                                    }
                                    else{
                                        val=true;
                                        break;
                                    }
                                    if(val)
                                    {
                                        break;
                                    }
                                }
                            }
                        }
                    }

                }

//                System.out.println(path_to_first);
//                System.out.println(path_to_second);

                boolean already_created_feature=false;
                int if_present_first_index=-1;
                for(int i=0;i<=index_features-1;i++)
                {
                    StringTokenizer st = new StringTokenizer(sys_features[i]," ");
                    String comp_name=st.nextToken();
                    String init_struct_name=ctx.Struct_noun(0).toString();
                    if(comp_name.equals(init_struct_name))
                    {
                        already_created_feature=true;
                        if_present_first_index=i;
                    }
                }

                if(already_created_feature)
                {
                    StringTokenizer st = new StringTokenizer(sys_features[if_present_first_index]," ");
                    String port_name=st.nextToken();
                    String valid_port_name="";
                    boolean has_found_valid_name=false;
                    while(st.hasMoreTokens())
                    {
                        port_name=st.nextToken();
                        if(port_name.equals(ctx.multi_flow(0).getText()+"_to_"+ctx.struct_multinoun().getText()))
                        {
                            int to_add_at_last=1;
                            while(port_name.equals(ctx.multi_flow(0).getText()+"_to_"+ctx.struct_multinoun().getText()+to_add_at_last))
                            {
                                to_add_at_last+=1;
                            }
                            valid_port_name= ctx.multi_flow(0).getText()+"_to_"+ctx.struct_multinoun().getText()+to_add_at_last;
                            has_found_valid_name=true;
                        }
                    }
                    if(has_found_valid_name)
                    {
                        sys_features[if_present_first_index]=sys_features[if_present_first_index]+" "+valid_port_name;
                    }
                    else {
                        sys_features[if_present_first_index]=sys_features[if_present_first_index]+" "+ctx.multi_flow(0).getText()+"_to_"+ctx.struct_multinoun().getText();
                    }
                    StringTokenizer get_last_port=new StringTokenizer(sys_features[if_present_first_index]," ");
                    String name_of_last_port="";
                    while(get_last_port.hasMoreTokens())
                    {
                        name_of_last_port=get_last_port.nextToken();
                    }
                    String new_decl="";
                    int second_line=1;
                    for(int i=0;i<=index_names-1;i++) {
                        if (ctx.Struct_noun(0).toString().equals(system_names[i])) {
                            for (int j = 0; j <= system_declaration[i][0].length() - 1; j++) {
                                if (system_declaration[i][0].charAt(j) == '\n' && second_line == 0) {
                                    new_decl=new_decl+"\n\t\t"+name_of_last_port+" : out data port;\n";
                                    second_line-=1;
                                }
                                else {
                                    new_decl=new_decl+system_declaration[i][0].charAt(j);
                                    if(system_declaration[i][0].charAt(j) == '\n')
                                    {
                                        second_line-=1;
                                    }
                                }

                            }
                            system_declaration[i][0]=new_decl;
                        }
                    }
                }
                else {
                    sys_features[index_features]=ctx.Struct_noun(0).toString();
//            System.out.println(ctx.struct_multinoun().getText());
                    String new_decl="";
                    sys_features[index_features]=sys_features[index_features]+" "+ctx.multi_flow(0).getText()+"_to_"+ctx.struct_multinoun().getText();
                    index_features+=1;
//            System.out.println("Increased");
                    int first_new_line=0;
                    for(int i=0;i<=index_names-1;i++) {
                        if (ctx.Struct_noun(0).toString().equals(system_names[i])) {
                            for (int j = 0; j <= system_declaration[i][0].length() - 1; j++) {
                                if (system_declaration[i][0].charAt(j) == '\n' && first_new_line == 0) {
                                    new_decl = new_decl + "\n\tfeatures\n";
                                    new_decl=new_decl+"\t\t"+ctx.multi_flow(0).getText()+"_to_"+ctx.struct_multinoun().getText()+" : out data port;\n";
                                    first_new_line+=1;
                                }
                                else {
                                    new_decl=new_decl+system_declaration[i][0].charAt(j);
                                }

                            }
                            system_declaration[i][0]=new_decl;
                        }
                    }
                }



                // For thr input port of the end comp
//        System.out.println("Start of second end");
                already_created_feature=false;
                if_present_first_index=-1;
                for(int i=0;i<=index_features-1;i++)
                {
                    StringTokenizer st = new StringTokenizer(sys_features[i]," ");
                    String comp_name=st.nextToken();
                    String init_struct_name=ctx.struct_multinoun().getText();
                    if(comp_name.equals(init_struct_name))
                    {
                        already_created_feature=true;
                        if_present_first_index=i;
                    }
                }
//        System.out.println(ctx.struct_multinoun().getText());
                if(already_created_feature)
                {
                    StringTokenizer st = new StringTokenizer(sys_features[if_present_first_index]," ");
                    String port_name=st.nextToken();
                    String valid_port_name="";
                    boolean has_found_valid_name=false;
//            System.out.println("haha");
                    while(st.hasMoreTokens())
                    {
                        port_name=st.nextToken();
                        if(port_name.equals(ctx.multi_flow(0).getText()+"_from_"+ctx.Struct_noun(0).toString()))
                        {   has_found_valid_name=true;
                            int to_add_at_last=1;
                            while(port_name.equals(ctx.multi_flow(0).getText()+"_from_"+ctx.Struct_noun(0).toString()+to_add_at_last))
                            {
                                to_add_at_last+=1;
                            }
                            valid_port_name= ctx.multi_flow(0).getText()+"_from_"+ctx.Struct_noun(0).toString()+to_add_at_last;
                        }
                    }
//            System.out.println("hehe");
                    if(has_found_valid_name)
                    {
                        sys_features[if_present_first_index]=sys_features[if_present_first_index]+" "+valid_port_name;
                    }
                    else {
                        sys_features[if_present_first_index]=sys_features[if_present_first_index]+" "+ctx.multi_flow(0).getText()+"_from_"+ctx.Struct_noun(0).toString();
                    }
                    StringTokenizer get_last_port=new StringTokenizer(sys_features[if_present_first_index]," ");
                    String name_of_last_port="";
                    while(get_last_port.hasMoreTokens())
                    {
                        name_of_last_port=get_last_port.nextToken();
                    }
//            System.out.println(name_of_last_port);
                    String new_decl="";
                    int second_line=1;
                    for(int i=0;i<=index_names-1;i++) {
                        if (ctx.struct_multinoun().getText().equals(system_names[i])) {
//                    System.out.println(ctx.struct_multinoun().getText());
                            for (int j = 0; j <= system_declaration[i][0].length() - 1; j++) {
                                if (system_declaration[i][0].charAt(j) == '\n' && second_line == 0) {
                                    new_decl=new_decl+"\n\t\t"+name_of_last_port+" : in data port;\n";
                                    second_line-=1;
                                }
                                else {
                                    new_decl=new_decl+system_declaration[i][0].charAt(j);
                                    if(system_declaration[i][0].charAt(j) == '\n')
                                    {
                                        second_line-=1;
                                    }
                                }

                            }
                            system_declaration[i][0]=new_decl;
                        }
                    }
                }
                else {
                    sys_features[index_features] = ctx.struct_multinoun().getText();
                    String new_decl = "";
                    sys_features[index_features] = sys_features[index_features] + " " + ctx.multi_flow(0).getText() + "_from_" + ctx.Struct_noun(0).toString();
                    index_features += 1;
                    int first_new_line = 0;
                    for (int i = 0; i <= index_names - 1; i++) {
                        if (ctx.struct_multinoun().getText().equals(system_names[i])) {
                            for (int j = 0; j <= system_declaration[i][0].length() - 1; j++) {
                                if (system_declaration[i][0].charAt(j) == '\n' && first_new_line == 0) {
                                    new_decl = new_decl + "\n\tfeatures\n";
                                    new_decl = new_decl + "\t\t" + ctx.multi_flow(0).getText() + "_from_" + ctx.Struct_noun(0).toString() + " : in data port;\n";
                                    first_new_line += 1;
                                } else {
                                    new_decl = new_decl + system_declaration[i][0].charAt(j);
                                }

                            }
                            system_declaration[i][0] = new_decl;
                        }
                    }
                }



                // For all intermediate for first path
                StringTokenizer split_first= new StringTokenizer(path_to_first,"-");
                int number_of_int_first=0;
                while(split_first.hasMoreTokens())
                {
                    String inter_sys=split_first.nextToken();
                    number_of_int_first+=1;
                }

                StringTokenizer split_second= new StringTokenizer(path_to_second,"-");
                int number_of_int_second=0;
                while(split_second.hasMoreTokens())
                {
                    String inter_sys=split_second.nextToken();
                    number_of_int_second+=1;
                }

                int copy_of_first=number_of_int_first;
                int copy_of_second=number_of_int_second;

                split_first= new StringTokenizer(path_to_first,"-");
                split_second=new StringTokenizer(path_to_second,"-");

                //System.out.println(path_to_first);
                //System.out.println(path_to_second);
                //System.out.println(number_of_int_first);
                //System.out.println(number_of_int_second);

                if(number_of_int_first>=3)
                {
                    while(copy_of_first>1)
                    {
                        if(copy_of_first==number_of_int_first)
                        {
                            split_first.nextToken();
                            copy_of_first-=1;
                        }
                        else
                        {
                            String name_of_comp=split_first.nextToken();
                            already_created_feature=false;
                            if_present_first_index=-1;
                            for(int i=0;i<=index_features-1;i++)
                            {
                                StringTokenizer st = new StringTokenizer(sys_features[i]," ");
                                String comp_name=st.nextToken();
                                String init_struct_name=name_of_comp;
                                if(comp_name.equals(init_struct_name))
                                {
                                    already_created_feature=true;
                                    if_present_first_index=i;
                                }
                            }

                            if(already_created_feature)
                            {
                                StringTokenizer st = new StringTokenizer(sys_features[if_present_first_index]," ");
                                String port_name=st.nextToken();
                                String valid_port_name="";
                                boolean has_found_valid_name=false;
                                while(st.hasMoreTokens())
                                {
                                    port_name=st.nextToken();
                                    if(port_name.equals(ctx.multi_flow(0).getText()+"_to_"+ctx.struct_multinoun().getText()))
                                    {
                                        int to_add_at_last=1;
                                        while(port_name.equals(ctx.multi_flow(0).getText()+"_to_"+ctx.struct_multinoun().getText()+to_add_at_last))
                                        {
                                            to_add_at_last+=1;
                                        }
                                        valid_port_name= ctx.multi_flow(0).getText()+"_to_"+ctx.struct_multinoun().getText()+to_add_at_last;
                                        has_found_valid_name=true;
                                    }
                                }
                                if(has_found_valid_name)
                                {
                                    sys_features[if_present_first_index]=sys_features[if_present_first_index]+" "+valid_port_name;
                                }
                                else {
                                    sys_features[if_present_first_index]=sys_features[if_present_first_index]+" "+ctx.multi_flow(0).getText()+"_to_"+ctx.struct_multinoun().getText();
                                }
                                StringTokenizer get_last_port=new StringTokenizer(sys_features[if_present_first_index]," ");
                                String name_of_last_port="";
                                while(get_last_port.hasMoreTokens())
                                {
                                    name_of_last_port=get_last_port.nextToken();
                                }
                                String new_decl="";
                                int second_line=1;
                                for(int i=0;i<=index_names-1;i++) {
                                    if (name_of_comp.equals(system_names[i])) {
                                        for (int j = 0; j <= system_declaration[i][0].length() - 1; j++) {
                                            if (system_declaration[i][0].charAt(j) == '\n' && second_line == 0) {
                                                new_decl=new_decl+"\n\t\t"+name_of_last_port+" : out data port;\n";
                                                second_line-=1;
                                            }
                                            else {
                                                new_decl=new_decl+system_declaration[i][0].charAt(j);
                                                if(system_declaration[i][0].charAt(j) == '\n')
                                                {
                                                    second_line-=1;
                                                }
                                            }

                                        }
                                        system_declaration[i][0]=new_decl;
                                    }
                                }
                            }
                            else {
                                sys_features[index_features]=name_of_comp;
//            System.out.println(ctx.struct_multinoun().getText());
                                String new_decl="";
                                sys_features[index_features]=sys_features[index_features]+" "+ctx.multi_flow(0).getText()+"_to_"+ctx.struct_multinoun().getText();
                                index_features+=1;
//            System.out.println("Increased");
                                int first_new_line=0;
                                for(int i=0;i<=index_names-1;i++) {
                                    if (name_of_comp.equals(system_names[i])) {
                                        for (int j = 0; j <= system_declaration[i][0].length() - 1; j++) {
                                            if (system_declaration[i][0].charAt(j) == '\n' && first_new_line == 0) {
                                                new_decl = new_decl + "\n\tfeatures\n";
                                                new_decl=new_decl+"\t\t"+ctx.multi_flow(0).getText()+"_to_"+ctx.struct_multinoun().getText()+" : out data port;\n";
                                                first_new_line+=1;
                                            }
                                            else {
                                                new_decl=new_decl+system_declaration[i][0].charAt(j);
                                            }

                                        }
                                        system_declaration[i][0]=new_decl;
                                    }
                                }
                            }



                            // For thr input port of the end comp
//        System.out.println("Start of second end");
                            already_created_feature=false;
                            if_present_first_index=-1;
                            for(int i=0;i<=index_features-1;i++)
                            {
                                StringTokenizer st = new StringTokenizer(sys_features[i]," ");
                                String comp_name=st.nextToken();
                                String init_struct_name=name_of_comp;
                                if(comp_name.equals(init_struct_name))
                                {
                                    already_created_feature=true;
                                    if_present_first_index=i;
                                }
                            }
//        System.out.println(ctx.struct_multinoun().getText());
                            if(already_created_feature)
                            {
                                StringTokenizer st = new StringTokenizer(sys_features[if_present_first_index]," ");
                                String port_name=st.nextToken();
                                String valid_port_name="";
                                boolean has_found_valid_name=false;
//            System.out.println("haha");
                                while(st.hasMoreTokens())
                                {
                                    port_name=st.nextToken();
                                    if(port_name.equals(ctx.multi_flow(0).getText()+"_from_"+ctx.Struct_noun(0).toString()))
                                    {   has_found_valid_name=true;
                                        int to_add_at_last=1;
                                        while(port_name.equals(ctx.multi_flow(0).getText()+"_from_"+ctx.Struct_noun(0).toString()+to_add_at_last))
                                        {
                                            to_add_at_last+=1;
                                        }
                                        valid_port_name= ctx.multi_flow(0).getText()+"_from_"+ctx.Struct_noun(0).toString()+to_add_at_last;
                                    }
                                }
//            System.out.println("hehe");
                                if(has_found_valid_name)
                                {
                                    sys_features[if_present_first_index]=sys_features[if_present_first_index]+" "+valid_port_name;
                                }
                                else {
                                    sys_features[if_present_first_index]=sys_features[if_present_first_index]+" "+ctx.multi_flow(0).getText()+"_from_"+ctx.Struct_noun(0).toString();
                                }
                                StringTokenizer get_last_port=new StringTokenizer(sys_features[if_present_first_index]," ");
                                String name_of_last_port="";
                                while(get_last_port.hasMoreTokens())
                                {
                                    name_of_last_port=get_last_port.nextToken();
                                }
//            System.out.println(name_of_last_port);
                                String new_decl="";
                                int second_line=1;
                                for(int i=0;i<=index_names-1;i++) {
                                    if (name_of_comp.equals(system_names[i])) {
//                    System.out.println(ctx.struct_multinoun().getText());
                                        for (int j = 0; j <= system_declaration[i][0].length() - 1; j++) {
                                            if (system_declaration[i][0].charAt(j) == '\n' && second_line == 0) {
                                                new_decl=new_decl+"\n\t\t"+name_of_last_port+" : in data port;\n";
                                                second_line-=1;
                                            }
                                            else {
                                                new_decl=new_decl+system_declaration[i][0].charAt(j);
                                                if(system_declaration[i][0].charAt(j) == '\n')
                                                {
                                                    second_line-=1;
                                                }
                                            }

                                        }
                                        system_declaration[i][0]=new_decl;
                                    }
                                }
                            }
                            else {
                                sys_features[index_features] = name_of_comp;
                                String new_decl = "";
                                sys_features[index_features] = sys_features[index_features] + " " + ctx.multi_flow(0).getText() + "_from_" + ctx.Struct_noun(0).toString();
                                index_features += 1;
                                int first_new_line = 0;
                                for (int i = 0; i <= index_names - 1; i++) {
                                    if (name_of_comp.equals(system_names[i])) {
                                        for (int j = 0; j <= system_declaration[i][0].length() - 1; j++) {
                                            if (system_declaration[i][0].charAt(j) == '\n' && first_new_line == 0) {
                                                new_decl = new_decl + "\n\tfeatures\n";
                                                new_decl = new_decl + "\t\t" + ctx.multi_flow(0).getText() + "_from_" + ctx.Struct_noun(0).toString() + " : in data port;\n";
                                                first_new_line += 1;
                                            } else {
                                                new_decl = new_decl + system_declaration[i][0].charAt(j);
                                            }

                                        }
                                        system_declaration[i][0] = new_decl;
                                    }
                                }
                            }

                            copy_of_first-=1;
                            if(copy_of_first==1)
                            {
                                break;
                            }

                        }
                    }
                }

                if(number_of_int_second>=3)
                {
                    while(copy_of_second>1)
                    {
                        if(copy_of_second==number_of_int_second)
                        {
                            split_second.nextToken();
                            copy_of_second-=1;
                            //System.out.println("Skip first");
                        }
                        else
                        {
                            String name_of_comp=split_second.nextToken();
                            //System.out.println(name_of_comp);
                            already_created_feature=false;
                            if_present_first_index=-1;
                            for(int i=0;i<=index_features-1;i++)
                            {
                                StringTokenizer st = new StringTokenizer(sys_features[i]," ");
                                String comp_name=st.nextToken();
                                String init_struct_name=name_of_comp;
                                if(comp_name.equals(init_struct_name))
                                {
                                    already_created_feature=true;
                                    if_present_first_index=i;
                                }
                            }

                            if(already_created_feature)
                            {
                                StringTokenizer st = new StringTokenizer(sys_features[if_present_first_index]," ");
                                String port_name=st.nextToken();
                                String valid_port_name="";
                                boolean has_found_valid_name=false;
                                while(st.hasMoreTokens())
                                {
                                    port_name=st.nextToken();
                                    if(port_name.equals(ctx.multi_flow(0).getText()+"_to_"+ctx.struct_multinoun().getText()))
                                    {
                                        int to_add_at_last=1;
                                        while(port_name.equals(ctx.multi_flow(0).getText()+"_to_"+ctx.struct_multinoun().getText()+to_add_at_last))
                                        {
                                            to_add_at_last+=1;
                                        }
                                        valid_port_name= ctx.multi_flow(0).getText()+"_to_"+ctx.struct_multinoun().getText()+to_add_at_last;
                                        has_found_valid_name=true;
                                    }
                                }
                                if(has_found_valid_name)
                                {
                                    sys_features[if_present_first_index]=sys_features[if_present_first_index]+" "+valid_port_name;
                                }
                                else {
                                    sys_features[if_present_first_index]=sys_features[if_present_first_index]+" "+ctx.multi_flow(0).getText()+"_to_"+ctx.struct_multinoun().getText();
                                }
                                StringTokenizer get_last_port=new StringTokenizer(sys_features[if_present_first_index]," ");
                                String name_of_last_port="";
                                while(get_last_port.hasMoreTokens())
                                {
                                    name_of_last_port=get_last_port.nextToken();
                                }
                                String new_decl="";
                                int second_line=1;
                                for(int i=0;i<=index_names-1;i++) {
                                    if (name_of_comp.equals(system_names[i])) {
                                        for (int j = 0; j <= system_declaration[i][0].length() - 1; j++) {
                                            if (system_declaration[i][0].charAt(j) == '\n' && second_line == 0) {
                                                new_decl=new_decl+"\n\t\t"+name_of_last_port+" : out data port;\n";
                                                second_line-=1;
                                            }
                                            else {
                                                new_decl=new_decl+system_declaration[i][0].charAt(j);
                                                if(system_declaration[i][0].charAt(j) == '\n')
                                                {
                                                    second_line-=1;
                                                }
                                            }

                                        }
                                        system_declaration[i][0]=new_decl;
                                    }
                                }
                            }
                            else {
                                sys_features[index_features]=name_of_comp;
//            System.out.println(ctx.struct_multinoun().getText());
                                String new_decl="";
                                sys_features[index_features]=sys_features[index_features]+" "+ctx.multi_flow(0).getText()+"_to_"+ctx.struct_multinoun().getText();
                                index_features+=1;
//            System.out.println("Increased");
                                int first_new_line=0;
                                for(int i=0;i<=index_names-1;i++) {
                                    if (name_of_comp.equals(system_names[i])) {
                                        for (int j = 0; j <= system_declaration[i][0].length() - 1; j++) {
                                            if (system_declaration[i][0].charAt(j) == '\n' && first_new_line == 0) {
                                                new_decl = new_decl + "\n\tfeatures\n";
                                                new_decl=new_decl+"\t\t"+ctx.multi_flow(0).getText()+"_to_"+ctx.struct_multinoun().getText()+" : out data port;\n";
                                                first_new_line+=1;
                                            }
                                            else {
                                                new_decl=new_decl+system_declaration[i][0].charAt(j);
                                            }

                                        }
                                        system_declaration[i][0]=new_decl;
                                    }
                                }
                            }



                            // For thr input port of the end comp
//        System.out.println("Start of second end");
                            already_created_feature=false;
                            if_present_first_index=-1;
                            for(int i=0;i<=index_features-1;i++)
                            {
                                StringTokenizer st = new StringTokenizer(sys_features[i]," ");
                                String comp_name=st.nextToken();
                                String init_struct_name=name_of_comp;
                                if(comp_name.equals(init_struct_name))
                                {
                                    already_created_feature=true;
                                    if_present_first_index=i;
                                }
                            }
//        System.out.println(ctx.struct_multinoun().getText());
                            if(already_created_feature)
                            {
                                StringTokenizer st = new StringTokenizer(sys_features[if_present_first_index]," ");
                                String port_name=st.nextToken();
                                String valid_port_name="";
                                boolean has_found_valid_name=false;
//            System.out.println("haha");
                                while(st.hasMoreTokens())
                                {
                                    port_name=st.nextToken();
                                    if(port_name.equals(ctx.multi_flow(0).getText()+"_from_"+ctx.Struct_noun(0).toString()))
                                    {   has_found_valid_name=true;
                                        int to_add_at_last=1;
                                        while(port_name.equals(ctx.multi_flow(0).getText()+"_from_"+ctx.Struct_noun(0).toString()+to_add_at_last))
                                        {
                                            to_add_at_last+=1;
                                        }
                                        valid_port_name= ctx.multi_flow(0).getText()+"_from_"+ctx.Struct_noun(0).toString()+to_add_at_last;
                                    }
                                }
//            System.out.println("hehe");
                                if(has_found_valid_name)
                                {
                                    sys_features[if_present_first_index]=sys_features[if_present_first_index]+" "+valid_port_name;
                                }
                                else {
                                    sys_features[if_present_first_index]=sys_features[if_present_first_index]+" "+ctx.multi_flow(0).getText()+"_from_"+ctx.Struct_noun(0).toString();
                                }
                                StringTokenizer get_last_port=new StringTokenizer(sys_features[if_present_first_index]," ");
                                String name_of_last_port="";
                                while(get_last_port.hasMoreTokens())
                                {
                                    name_of_last_port=get_last_port.nextToken();
                                }
//            System.out.println(name_of_last_port);
                                String new_decl="";
                                int second_line=1;
                                for(int i=0;i<=index_names-1;i++) {
                                    if (name_of_comp.equals(system_names[i])) {
//                    System.out.println(ctx.struct_multinoun().getText());
                                        for (int j = 0; j <= system_declaration[i][0].length() - 1; j++) {
                                            if (system_declaration[i][0].charAt(j) == '\n' && second_line == 0) {
                                                new_decl=new_decl+"\n\t\t"+name_of_last_port+" : in data port;\n";
                                                second_line-=1;
                                            }
                                            else {
                                                new_decl=new_decl+system_declaration[i][0].charAt(j);
                                                if(system_declaration[i][0].charAt(j) == '\n')
                                                {
                                                    second_line-=1;
                                                }
                                            }

                                        }
                                        system_declaration[i][0]=new_decl;
                                    }
                                }
                            }
                            else {
                                sys_features[index_features] = name_of_comp;
                                String new_decl = "";
                                sys_features[index_features] = sys_features[index_features] + " " + ctx.multi_flow(0).getText() + "_from_" + ctx.Struct_noun(0).toString();
                                index_features += 1;
                                int first_new_line = 0;
                                for (int i = 0; i <= index_names - 1; i++) {
                                    if (name_of_comp.equals(system_names[i])) {
                                        for (int j = 0; j <= system_declaration[i][0].length() - 1; j++) {
                                            if (system_declaration[i][0].charAt(j) == '\n' && first_new_line == 0) {
                                                new_decl = new_decl + "\n\tfeatures\n";
                                                new_decl = new_decl + "\t\t" + ctx.multi_flow(0).getText() + "_from_" + ctx.Struct_noun(0).toString() + " : in data port;\n";
                                                first_new_line += 1;
                                            } else {
                                                new_decl = new_decl + system_declaration[i][0].charAt(j);
                                            }

                                        }
                                        system_declaration[i][0] = new_decl;
                                    }
                                }
                            }

                            copy_of_second-=1;
                            if(copy_of_second==1)
                            {
                                break;
                            }

                        }
                    }

                }
                get_updated_decl(ctx, under_same_component, path_to_first, path_to_second);
            }
        }
        else
        {

        }
        return null;
    }


    public void get_updated_decl(New_grammarParser.Functional_stmtContext ctx, boolean under_same_component, String path_to_first, String path_to_second)
    {
        if(under_same_component)
        {
            int index_of_upper_comp=-1;
            String upper_comp="";
            for(int i=0;i<=index_subcomponents-1;i++)
            {
                if(system_subcomponents[i].contains(ctx.Struct_noun(0).toString()) && system_subcomponents[i].contains(ctx.struct_multinoun().Struct_noun(0).getText()))
                {
                    StringTokenizer st=new StringTokenizer(system_subcomponents[i]," ");
                    upper_comp=st.nextToken();
                }
            }
            for(int i=0; i<=index_names-1;i++)
            {
                if(system_names[i].equals(upper_comp))
                {
                    index_of_upper_comp=i;
                }
            }
            int count_of_connections=0;
            boolean already_connections=false;
            for(int i=0;i<=index_connections-1;i++)
            {
                if(connections[i].equals(upper_comp))
                {
                    already_connections=true;
                    count_of_connections=number_of_connections[i];
                }
            }
            String new_decl="";
            int nextline_char=0;
            String port_name_of_first="";
            String port_name_of_second="";

            for(int i=0;i<=index_features-1;i++)
            {
                StringTokenizer st= new StringTokenizer(sys_features[i]," ");
                String name_of_comp_having_features=st.nextToken();
                if(ctx.Struct_noun(0).toString().equals(name_of_comp_having_features))
                {
                    while(st.hasMoreTokens())
                    {
                        port_name_of_first=st.nextToken();
                    }
                }
                else if(ctx.struct_multinoun().Struct_noun(0).getText().equals(name_of_comp_having_features))
                {
                    while(st.hasMoreTokens())
                    {
                        port_name_of_second=st.nextToken();
                    }
                }
                else {

                }
            }

            for(int i=system_declaration[index_of_upper_comp][1].length()-1; i>=0; i--)
            {
                if(system_declaration[index_of_upper_comp][1].charAt(i)=='\n' && nextline_char==1)
                {
                    if(already_connections)
                    {
                        for(int k=0;k<=index_connections-1;k++)
                        {
                            if(upper_comp.equals(connections[k]))
                            {
                                number_of_connections[k]+=1;
                                new_decl="\n\t\t"+upper_comp+count_of_connections+": port "+"this_"+ctx.Struct_noun(0).toString()+"."+port_name_of_first+"->this_"+ctx.struct_multinoun().Struct_noun(0).getText()+"."+port_name_of_second+";\n" + new_decl;
                                update_list_flows(upper_comp, ctx.Struct_noun(0).toString(), port_name_of_first , ctx.struct_multinoun().Struct_noun(0).getText(), port_name_of_second);
                                break;
                            }
                        }
                    }
                    else {
                        connections[index_connections]=upper_comp;
                        index_connections+=1;
                        number_of_connections[index_connections-1]=1;
                        new_decl="\n\tconnections\n"+"\t\t"+upper_comp+"0"+": port "+"this_"+ctx.Struct_noun(0).toString()+"."+port_name_of_first+"->this_"+ctx.struct_multinoun().Struct_noun(0).getText()+"."+port_name_of_second+";\n" + new_decl;
                        update_list_flows(upper_comp, ctx.Struct_noun(0).toString(), port_name_of_first ,ctx.struct_multinoun().Struct_noun(0).getText(), port_name_of_second);
                    }
                    nextline_char+=1;
                }
                else
                {
                    if(system_declaration[index_of_upper_comp][1].charAt(i)=='\n')
                    {
                        nextline_char+=1;
                    }
                    new_decl=system_declaration[index_of_upper_comp][1].charAt(i)+new_decl;
                }
            }
            system_declaration[index_of_upper_comp][1]=new_decl;
        }
        else {

            //System.out.println(path_to_first);
            //System.out.println(path_to_second);


            int number_of_int_first = 0;
            int number_of_int_second = 0;

            StringTokenizer split_first = new StringTokenizer(path_to_first, "-");
            StringTokenizer split_second = new StringTokenizer(path_to_second, "-");

            while (split_first.hasMoreTokens()) {
                number_of_int_first += 1;
                split_first.nextToken();
            }

            while (split_second.hasMoreTokens()) {
                number_of_int_second += 1;
                split_second.nextToken();
            }

            //For intermediate systems within the path of first and second
            if (number_of_int_first >= 3) {
                String ind_sys_first[] = new String[number_of_int_first - 1];
                int index_of_ind_sys_first = 0;
                split_first = new StringTokenizer(path_to_first, "-");
                split_first.nextToken();
                while (split_first.hasMoreTokens()) {
                    ind_sys_first[index_of_ind_sys_first] = split_first.nextToken();
                    index_of_ind_sys_first += 1;
                }
                String upper_comp = "";
                for (int k = 0; k <= index_of_ind_sys_first - 2; k++) {
                    for (int l = 0; l <= index_subcomponents - 1; l++) {
                        if (system_subcomponents[l].contains(ind_sys_first[k]) && system_subcomponents[l].contains(ind_sys_first[k + 1])) {
                            StringTokenizer st = new StringTokenizer(system_subcomponents[l], " ");
                            upper_comp = st.nextToken();
                        }
                    }

                    int index_of_upper_comp = -1;
                    for (int i = 0; i <= index_names - 1; i++) {
                        if (system_names[i].equals(upper_comp)) {
                            index_of_upper_comp = i;
                        }
                    }
                    int count_of_connections = 0;
                    boolean already_connections = false;
                    for (int i = 0; i <= index_connections - 1; i++) {
                        if (connections[i].equals(upper_comp)) {
                            already_connections = true;
                            count_of_connections = number_of_connections[i];
                        }
                    }
                    String new_decl = "";
                    int nextline_char = 0;
                    String port_name_of_first = "";
                    String port_name_of_second = "";

                    for (int i = 0; i <= index_features - 1; i++) {
                        StringTokenizer st = new StringTokenizer(sys_features[i], " ");
                        String name_of_comp_having_features = st.nextToken();
                        String second_last_comp = "";
                        if (ind_sys_first[k].equals(name_of_comp_having_features)) {
                            while (st.hasMoreTokens()) {
                                port_name_of_first = st.nextToken();
                            }
                        } else if (ind_sys_first[k + 1].equals(name_of_comp_having_features)) {
                            while (st.hasMoreTokens()) {
                                second_last_comp = port_name_of_second;
                                port_name_of_second = st.nextToken();
                            }
                            if(k==index_of_ind_sys_first-2)
                            {

                            }
                            else {
                                port_name_of_second=second_last_comp;
                            }
                        } else {

                        }
                    }

                    for (int i = system_declaration[index_of_upper_comp][1].length() - 1; i >= 0; i--) {
                        if (system_declaration[index_of_upper_comp][1].charAt(i) == '\n' && nextline_char == 1) {
                            if (already_connections) {
                                for (int m = 0; m <= index_connections - 1; m++) {
                                    if (upper_comp.equals(connections[m])) {
                                        number_of_connections[m] += 1;
                                        new_decl = "\n\t\t" + upper_comp + count_of_connections + ": port " + "this_" + ind_sys_first[k+1] + "." + port_name_of_second + "->this_" + ind_sys_first[k] + "." + port_name_of_first + ";\n" + new_decl;
                                        update_list_flows(upper_comp, ind_sys_first[k], port_name_of_first ,ind_sys_first[k+1], port_name_of_second);
                                        break;
                                    }
                                }
                            } else {
                                connections[index_connections] = upper_comp;
                                index_connections += 1;
                                number_of_connections[index_connections - 1] = 1;
                                new_decl = "\n\tconnections\n" + "\t\t" + upper_comp + "0" + ": port " + "this_" + ind_sys_first[k+1] + "." + port_name_of_second + "->this_" + ind_sys_first[k] + "." + port_name_of_first + ";\n" + new_decl;
                                update_list_flows(upper_comp, ind_sys_first[k], port_name_of_first ,ind_sys_first[k+1], port_name_of_second);
                            }
                            nextline_char += 1;
                        } else {
                            if (system_declaration[index_of_upper_comp][1].charAt(i) == '\n') {
                                nextline_char += 1;
                            }
                            new_decl = system_declaration[index_of_upper_comp][1].charAt(i) + new_decl;
                        }
                    }
                    system_declaration[index_of_upper_comp][1] = new_decl;
//                    System.out.println(system_declaration[index_of_upper_comp][1]);

                }
            }
            if (number_of_int_second >= 3) {
                String ind_sys_second[] = new String[number_of_int_second - 1];
                int index_of_ind_sys_second = 0;
                split_second = new StringTokenizer(path_to_second, "-");
                split_second.nextToken();
                while (split_second.hasMoreTokens()) {
                    ind_sys_second[index_of_ind_sys_second] = split_second.nextToken();
                    index_of_ind_sys_second += 1;
                }

                String upper_comp = "";
                for (int k = 0; k <= index_of_ind_sys_second - 2; k++) {
                    for (int l = 0; l <= index_subcomponents - 1; l++) {
                        if (system_subcomponents[l].contains(ind_sys_second[k]) && system_subcomponents[l].contains(ind_sys_second[k + 1])) {
                            StringTokenizer st = new StringTokenizer(system_subcomponents[l], " ");
                            upper_comp = st.nextToken();
                        }
                    }

                    int index_of_upper_comp = -1;
                    for (int i = 0; i <= index_names - 1; i++) {
                        if (system_names[i].equals(upper_comp)) {
                            index_of_upper_comp = i;
                        }
                    }
                    int count_of_connections = 0;
                    boolean already_connections = false;
                    for (int i = 0; i <= index_connections - 1; i++) {
                        if (connections[i].equals(upper_comp)) {
                            already_connections = true;
                            count_of_connections = number_of_connections[i];
                        }
                    }
                    String new_decl = "";
                    int nextline_char = 0;
                    String port_name_of_first = "";
                    String port_name_of_second = "";

                    for (int i = 0; i <= index_features - 1; i++) {
                        StringTokenizer st = new StringTokenizer(sys_features[i], " ");
                        String name_of_comp_having_features = st.nextToken();
                        String second_last_port="";
                        if (ind_sys_second[k].equals(name_of_comp_having_features)) {
                            while (st.hasMoreTokens()) {
                                second_last_port=port_name_of_first;
                                port_name_of_first = st.nextToken();
                            }
                            port_name_of_first=second_last_port;
                        } else if (ind_sys_second[k + 1].equals(name_of_comp_having_features)) {
                            while (st.hasMoreTokens()) {
                                port_name_of_second = st.nextToken();
                            }
                        } else {

                        }
                    }

                    for (int i = system_declaration[index_of_upper_comp][1].length() - 1; i >= 0; i--) {
                        if (system_declaration[index_of_upper_comp][1].charAt(i) == '\n' && nextline_char == 1) {
                            if (already_connections) {
                                for (int m = 0; m <= index_connections - 1; m++) {
                                    if (upper_comp.equals(connections[m])) {
                                        number_of_connections[m] += 1;
                                        new_decl = "\n\t\t" + upper_comp + count_of_connections + ": port " + "this_" + ind_sys_second[k] + "." + port_name_of_first + "->this_" + ind_sys_second[k + 1] + "." + port_name_of_second + ";\n" + new_decl;
                                        update_list_flows(upper_comp, ind_sys_second[k], port_name_of_first ,ind_sys_second[k+1], port_name_of_second);
                                        break;
                                    }
                                }
                            } else {
                                connections[index_connections] = upper_comp;
                                index_connections += 1;
                                number_of_connections[index_connections - 1] = 1;
                                new_decl = "\n\tconnections\n" + "\t\t" + upper_comp + "0" + ": port " + "this_" + ind_sys_second[k] + "." + port_name_of_first + "->this_" + ind_sys_second[k + 1] + "." + port_name_of_second + ";\n" + new_decl;
                                update_list_flows(upper_comp, ind_sys_second[k], port_name_of_first ,ind_sys_second[k+1], port_name_of_second);
                            }
                            nextline_char += 1;
                        } else {
                            if (system_declaration[index_of_upper_comp][1].charAt(i) == '\n') {
                                nextline_char += 1;
                            }
                            new_decl = system_declaration[index_of_upper_comp][1].charAt(i) + new_decl;
                        }
                    }
                    system_declaration[index_of_upper_comp][1] = new_decl;
                    //System.out.println(system_declaration[index_of_upper_comp][1]);

                }
            }


            //For connections between first of p1 and first of p2
            String comm_conn_first = "";
            String comm_conn_second = "";
            split_first = new StringTokenizer(path_to_first, "-");
            split_second = new StringTokenizer(path_to_second, "-");
            split_first.nextToken();
            comm_conn_first = split_first.nextToken();
            split_second.nextToken();
            comm_conn_second = split_second.nextToken();

            int index_of_upper_comp=-1;
            String upper_comp="";
            for(int i=0;i<=index_subcomponents-1;i++)
            {
                if(system_subcomponents[i].contains(comm_conn_first) && system_subcomponents[i].contains(comm_conn_second))
                {
                    StringTokenizer st=new StringTokenizer(system_subcomponents[i]," ");
                    upper_comp=st.nextToken();
                }
            }
            for(int i=0; i<=index_names-1;i++)
            {
                if(system_names[i].equals(upper_comp))
                {
                    index_of_upper_comp=i;
                }
            }
            int count_of_connections=0;
            boolean already_connections=false;
            for(int i=0;i<=index_connections-1;i++)
            {
                if(connections[i].equals(upper_comp))
                {
                    already_connections=true;
                    count_of_connections=number_of_connections[i];
                }
            }
            String new_decl="";
            int nextline_char=0;
            String port_name_of_first="";
            String port_name_of_second="";

            for(int i=0;i<=index_features-1;i++)
            {
                StringTokenizer st= new StringTokenizer(sys_features[i]," ");
                String name_of_comp_having_features=st.nextToken();
                String second_last_conn="";
                if(comm_conn_first.equals(name_of_comp_having_features))
                {
                    while(st.hasMoreTokens())
                    {
                        second_last_conn=port_name_of_first;
                        port_name_of_first=st.nextToken();
                    }
                    if(number_of_int_first>=3)
                    {
                        port_name_of_first=second_last_conn;
                    }
                }
                else if(comm_conn_second.equals(name_of_comp_having_features))
                {
                    while(st.hasMoreTokens())
                    {
                        port_name_of_second=st.nextToken();
                    }
                }
                else {

                }
            }


            for(int i=system_declaration[index_of_upper_comp][1].length()-1; i>=0; i--)
            {
                if(system_declaration[index_of_upper_comp][1].charAt(i)=='\n' && nextline_char==1)
                {
                    if(already_connections)
                    {
                        for(int k=0;k<=index_connections-1;k++)
                        {
                            if(upper_comp.equals(connections[k]))
                            {
                                number_of_connections[k]+=1;
                                new_decl="\n\t\t"+upper_comp+count_of_connections+": port "+"this_"+comm_conn_first+"."+port_name_of_first+"->this_"+comm_conn_second+"."+port_name_of_second+";\n" + new_decl;
                                update_list_flows(upper_comp, comm_conn_first, port_name_of_first ,comm_conn_second, port_name_of_second);
                                break;
                            }
                        }
                    }
                    else {
                        connections[index_connections]=upper_comp;
                        index_connections+=1;
                        number_of_connections[index_connections-1]=1;
                        new_decl="\n\tconnections\n"+"\t\t"+upper_comp+"0"+": port "+"this_"+comm_conn_first+"."+port_name_of_first+"->this_"+comm_conn_second+"."+port_name_of_second+";\n" + new_decl;
                        update_list_flows(upper_comp, comm_conn_first, port_name_of_first ,comm_conn_second, port_name_of_second);
                    }
                    nextline_char+=1;
                }
                else
                {
                    if(system_declaration[index_of_upper_comp][1].charAt(i)=='\n')
                    {
                        nextline_char+=1;
                    }
                    new_decl=system_declaration[index_of_upper_comp][1].charAt(i)+new_decl;
                }
            }
            system_declaration[index_of_upper_comp][1]=new_decl;

        }
    }

    public void update_list_flows(String upper_comp, String first_name, String port_name_of_first, String second_name, String port_name_of_second)
    {
        if(upper_comp.equals(first_name))
        {
            for(int c=0;c<=ind_system_in_features-1;c++)
            {
                StringTokenizer st4=new StringTokenizer(system_in_features[c]," ");
                String get_comp_name_at_start=st4.nextToken();
                if(upper_comp.equals(get_comp_name_at_start))
                {
                    system_in_features[c]=system_in_features[c]+" "+port_name_of_first;
                }
            }
        }
        else if(upper_comp.equals(second_name))
        {
            for(int c=0;c<=ind_system_in_features-1;c++)
            {
                StringTokenizer st4=new StringTokenizer(system_in_features[c]," ");
                String get_comp_name_at_start=st4.nextToken();
                if(upper_comp.equals(get_comp_name_at_start))
                {
                    system_in_features[c]=system_in_features[c]+" "+port_name_of_second;
                }
            }
        }
    }
    /**
     * Visit a parse tree produced by {@link New_grammarParser#struct_multinoun}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    @Override
    public Object visitStruct_multinoun(New_grammarParser.Struct_multinounContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * Visit a parse tree produced by {@link New_grammarParser#multi_flow}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    @Override
    public Object visitMulti_flow(New_grammarParser.Multi_flowContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * Visit a parse tree produced by {@link New_grammarParser#flow}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    @Override
    public Object visitFlow(New_grammarParser.FlowContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * Visit a parse tree produced by {@link New_grammarParser#states}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    @Override
    public Object visitStates(New_grammarParser.StatesContext ctx) {
        return visitChildren(ctx);
    }
}