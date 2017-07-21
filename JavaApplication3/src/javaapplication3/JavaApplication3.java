/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package javaapplication3;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author AHO
 */
class Folder{
  public List<String> subFolder;  
  public List<String> subFiles;
  public List<Double> subFolder_size;
  public List<Double> subFiles_size;
  public String FolderPath;
  
//  Folder(String Path_OfFolder){
//      
//  
//  }
  Folder(String Path_ofFolder) throws IOException{
      subFolder = new ArrayList<String>();
      subFiles = new ArrayList<String>();
      subFolder_size = new ArrayList<Double>();
      subFiles_size = new ArrayList<Double>();
      FolderPath = Path_ofFolder;
      this.sizeOf_File_or_Folder(Path_ofFolder);
      this.list_of_embded_FileOrFolder(Path_ofFolder);
  }
Folder (){
      subFolder = new ArrayList<String>();
      subFiles = new ArrayList<String>();
      subFolder_size = new ArrayList<Double>();
      subFiles_size = new ArrayList<Double>();
      FolderPath = "C://";
}

        public double sizeOf_File_or_Folder(String PathOf_File_or_Folder)  throws IOException {
        // TODO code application logic here
        File file = null;
        File temp = null;
        double d = 0;
        double Size = 0;
        try {      
            file = new File(PathOf_File_or_Folder);
            String[] patths = file.list();
            for(String patth:patths) {
                temp = new File(PathOf_File_or_Folder + "/" + patth);
                if (temp.exists()){
                    if(temp.isFile()){
                        Path path = FileSystems.getDefault().getPath(PathOf_File_or_Folder + "/" + patth);
                        d = Files.size(path);
                        subFiles_size.add(d);
                        //return d;
                    }else if (temp.isDirectory()){
                        d = 0;
                        d = this.sizeOf_File_or_Folder_recurrsive(PathOf_File_or_Folder + "/" + patth);
                        subFolder_size.add(d);
                    }
                }else{
                    System.out.println("this directory dosent existe");
                }
            }
        }catch(Exception e) {
            // if any error occurs
            e.printStackTrace();
        }

        return 0;
    }
  
      public double sizeOf_File_or_Folder_recurrsive(String PathOf_File_or_Folder)  throws IOException {
        // TODO code application logic here
        File file = null;
        double d = 0;
        double Size = 0;
        try {      
            file = new File(PathOf_File_or_Folder);
            if (file.exists()){
                if(file.isFile()){
                    Path path = FileSystems.getDefault().getPath(PathOf_File_or_Folder);
                    d = Files.size(path);
                    //subFiles_size.add(d);
                    return d;
                }else if (file.isDirectory()){
                    String[] patths = file.list();
                    for(String patth:patths) {
                        Size += this.sizeOf_File_or_Folder_recurrsive(PathOf_File_or_Folder + "/" + patth);
                    }
                    d = Size;
                    //subFolder_size.add(d);
                    return d;
                }
            }else{
                System.out.println("this directory dosent existe");
            }
        }catch(Exception e) {
            // if any error occurs
            e.printStackTrace();
        }

        return 0;
    }
  public void print_the_embded_filesandfolder(){
      System.out.println(FolderPath);
      for (int i = 0; i<subFiles_size.size(); i++){
          System.out.println("\tsubFiles = " + subFiles.get(i) + " : " + subFiles_size.get(i));
      }
      for (int i = 0; i<subFolder_size.size(); i++){
          System.out.println("\tsubFolder = " + subFolder.get(i) + " : " + subFolder_size.get(i));
      }
  }
  public void print_the_embded_filesandfolder_asNameOrSize(int choise){
      int size = subFiles.size();
      int size2 = subFolder.size(); 
      if(choise == 1){
          int i = 0, j = 0;
          while(j<size){
              while(i<size2){
                  if(subFiles.get(i).compareTo(subFolder.get(j))<0){
                      System.out.println("\tsubFiles = " + subFiles.get(i) + " : " + subFiles_size.get(i));
                      i++;
                      if(i==size)break;
                  }else{
                      System.out.println("\tsubFolder = " + subFolder.get(j) + " : " + subFolder_size.get(j));
                      j++;
                      if(j==size2)break;
                  }
              }
          }
          while(i<size){
              System.out.println("\tsubFiles = " + subFiles.get(i) + " : " + subFiles_size.get(i));
              i++;
          }
          while(j<size2){
              System.out.println("\tsubFolder = " + subFolder.get(j) + " : " + subFolder_size.get(j));
              j++;
          }
      }else if(choise == 2){
          double min = -1;
          boolean goforword = false;
          int[] mini = new int[size];
          for(int i = 0; i<size; i++){
              mini[i] = -1;
          }
          for (int i = 0; i<size; i++){
              for (int j = 0; j<size; j++){
                  for(int h = 0; h<size; h++){
                      if(j==mini[h]){
                          goforword = true;
                      }
                  }
                  if(!goforword){
                      if(subFiles_size.get(j)>min){
                          mini[i] = j;
                          min = subFiles_size.get(j);
                      }
                  }
                  goforword = false;
              }
              min = -1;
          }
          double min2 = -1;
          goforword = false;
          int[] mini2 = new int[subFolder_size.size()];
            for(int i = 0; i<size2; i++){
              mini2[i] = -1;
          }
          for (int i = 0; i<size2; i++){
              for (int j = 0; j<size2; j++){
                  for(int h = 0; h<size2; h++){
                      if(j==mini2[h]){
                          goforword = true;
                      }
                  }
                  if(!goforword){
                      if(subFolder_size.get(j)>min2){
                          mini2[i] = j;
                          min2 = subFolder_size.get(j);
                      }
                  }
                  goforword = false;
              }
              min2 = -1;
          }
          int i = 0, j = 0;
          while(j<size){
              while(i<size2){
                  if(subFiles_size.get(mini[i])>subFolder_size.get(mini2[j])){
                      System.out.println("\tsubFiles = " + subFiles.get(mini[i]) + " : " + subFiles_size.get(mini[i]));
                      i++;
                      if(i==size)break;
                  }else{
                      System.out.println("\tsubFolder = " + subFolder.get(mini2[j]) + " : " + subFolder_size.get(mini2[j]));
                      j++;
                      if(j==size2)break;
                  }
              }
          }
          while(i<size){
              System.out.println("\tsubFiles = " + subFiles.get(mini[i]) + " : " + subFiles_size.get(mini[i]));
              i++;
          }
          while(j<size2){
              System.out.println("\tsubFolder = " + subFolder.get(mini2[j]) + " : " + subFolder_size.get(mini2[j]));
              j++;
          }
      }    
  }

    public String[] list_of_embded_FileOrFolder(String path_OfFileOrFolder)  throws IOException {
        String[] s = null;
        String[] f = new String[1];
        File subfile = null;
        boolean d = false;
        try {   
            File file = new File(path_OfFileOrFolder);
            s = file.list();
            for(String ss:s){
                d = true;
                subfile = new File(path_OfFileOrFolder + "//" + ss);
                if(subfile.isDirectory()){
                    subFolder.add(ss);
                }else if (subfile.isFile()){
                    subFiles.add(ss);
                }
            }
            if(d){
                return s;
            }else if (!d){
                return f;
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /************ Question 4 1  *************/
//    public String searchAbout_File_or_Folder (String b)  throws IOException {
//        String sol = "Directory dosent existe";
//        try {   
//            if(b.length()>5){
//                if(b.charAt(b.length()-3)=='.' || b.charAt(b.length()-4)=='.' ){
//                    System.out.println("this is File not Folder");
//                    return null;
//                }
//            }
//            Drive drivee = new Drive();
//            String[] s = drivee.details_about_disk_drive(false);
//            for (int i = 0; i<s.length; i++){
//                if(s[i] != null){
//                    //System.out.println("sol = tryy(" + s[i] + ", " + b + ")");
//                    sol = searchAbout_File_or_Folder(s[i], b);
//                    //System.out.println("sol == " + sol);
//                    if (sol != null) break;
//                }
//            }
//        }catch(Exception e) {
//            // if any error occurs
//            e.printStackTrace();
//        }
//        return sol;
//    }
    /************ Question 4 2  *************/
    public String searchAbout_File_or_Folder(String a, String b)  throws IOException {
        // TODO code application logic here
        File file = new File(a);
        File temp = null;
        String sol;
        String[] patths;
        try {   
            patths = file.list();
            if (patths == null) return null;
            for(String patth:patths) {
                if(Objects.equals(patth, b) == true){ 
                    System.out.println(a + "\\" + patth);
                    return a + "\\" + patth;
                }else{
                    temp = new File(a + "\\" + patth);
                    if(temp.isDirectory()){
                        sol = searchAbout_File_or_Folder (a + "\\" + patth, b);
                        if(sol != null){
                            return sol;
                        }
                    }
                    }
                }
            
            System.out.println("Directory dosent existe");
            
        }catch(Exception e) {
            // if any error occurs
            e.printStackTrace();
        }
        return null;
    }
        /************ Question 6  1  *************/
    public void delete_File_Or_Folder(String path_Of_FileOrFolder_ToRemove)  throws Exception {
        Path path = FileSystems.getDefault().getPath(path_Of_FileOrFolder_ToRemove);
        File file = new File(path_Of_FileOrFolder_ToRemove);
        try {
            if (file.isDirectory()){
                this.delete_File_Or_Folder_recurrsive(path_Of_FileOrFolder_ToRemove);
            }else {
                System.out.println("this is File not Folder");
                return ;
            }
    
        }catch(Exception e) {
            // if any error occurs
            e.printStackTrace();
        }
    }
    /************ Question 6  2  *************/
    public void delete_File_Or_Folder_recurrsive(String path_Of_FileOrFolder_ToRemove)  throws Exception {
        Path path = FileSystems.getDefault().getPath(path_Of_FileOrFolder_ToRemove);
        File file = new File(path_Of_FileOrFolder_ToRemove);
        try {
            if (file.isDirectory()){
                String[] s = this.list_of_embded_FileOrFolder(path_Of_FileOrFolder_ToRemove);
                if(s[0] == null){
                    Files.delete(path);
                }
                else{
                    for (String ss:s){
                        delete_File_Or_Folder_recurrsive(path_Of_FileOrFolder_ToRemove + "/" + ss);
                    }
                    Files.delete(path);
                }
            }else {
                Files.delete(path);
            }
    
        } catch (NoSuchFileException x) {
            System.err.format("%s: no such" + " file or directory%n", path);
        } catch (DirectoryNotEmptyException x) {
            System.err.format("%s not empty%n", path);
        } catch (IOException x) {
            // File permission problems are caught here.
            System.err.println(x);
        }
    }
        public void create_New_FileOrFolder(String path_OfFileOrFolder_To_Create, String name_OfFileOrFolder)  throws IOException {
        // TODO code application logic here
        File file = null;
        String newFile1, newFile2;
        try {   
            Path path = FileSystems.getDefault().getPath(path_OfFileOrFolder_To_Create + "/" + name_OfFileOrFolder);
            file = new File(path_OfFileOrFolder_To_Create + "/" + name_OfFileOrFolder);
            newFile1 = file.getName().substring(file.getName().length() - 4, file.getName().length() -3);
            newFile2 = file.getName().substring(file.getName().length() - 5, file.getName().length() -4);
            
            String[] paths = this.list_of_embded_FileOrFolder(path_OfFileOrFolder_To_Create);
            if(Objects.equals(newFile1, ".") || Objects.equals(newFile2, ".")){
                System.out.println("this is File not Folder");
                return ;
            }
            for(String pathh:paths) {               
               if (Objects.equals(pathh, name_OfFileOrFolder)){
                   System.out.println("the Directory that you want to create is already existe");
                   return ;
               }
            }
            //System.out.println(newFile);
            if(!Objects.equals(newFile1, ".") && !Objects.equals(newFile2, ".")){
                file.mkdir();
            }
        }catch(Exception e) {
            // if any error occurs
            e.printStackTrace();
        }
    }
};
class FILE {
    String PathofFile;

    FILE(String Path_ofFile) throws IOException{
        PathofFile = new String(Path_ofFile);
    }
            /************ Question 5  1  *************/
    public void create_New_FileOrFolder(String path_OfFileOrFolder_To_Create, String name_OfFileOrFolder)  throws IOException {
        // TODO code application logic here
        File file = null;
        String newFile1, newFile2;
        Folder folder = new Folder();
        try {   
            Path path = FileSystems.getDefault().getPath(path_OfFileOrFolder_To_Create + "/" + name_OfFileOrFolder);
            file = new File(path_OfFileOrFolder_To_Create + "/" + name_OfFileOrFolder);
            newFile1 = file.getName().substring(file.getName().length() - 4, file.getName().length() -3);
            newFile2 = file.getName().substring(file.getName().length() - 5, file.getName().length() -4);
            
            String[] paths = folder.list_of_embded_FileOrFolder(path_OfFileOrFolder_To_Create);
            if(!Objects.equals(newFile1, ".") && !Objects.equals(newFile2, ".")){
                System.out.println("this is Folder not File");
                return ;
            }
            for(String pathh:paths) {               
               if (Objects.equals(pathh, name_OfFileOrFolder)){
                   System.out.println("the Directory that you want to create is already existe");
                   return ;
               }
            }
            //System.out.println(newFile);
            if(Objects.equals(newFile1, ".") || Objects.equals(newFile2, ".")){
                Files.createFile(path);
                //file.createNewFile();
            }
        }catch(Exception e) {
            // if any error occurs
            e.printStackTrace();
        }
    }
    public void delete_File_Or_Folder(String path_Of_FileOrFolder_ToRemove)  throws Exception {
        Path path = FileSystems.getDefault().getPath(path_Of_FileOrFolder_ToRemove);
        File file = new File(path_Of_FileOrFolder_ToRemove);
        Folder folder = new Folder();
        try {
            if (file.isDirectory()){
                System.out.println("this is Folder not File");
                return ;
            }else {
                Files.delete(path);
            }
    
        } catch (NoSuchFileException x) {
            System.err.format("%s: no such" + " file or directory%n", path);
        } catch (DirectoryNotEmptyException x) {
            System.err.format("%s not empty%n", path);
        } catch (IOException x) {
            // File permission problems are caught here.
            System.err.println(x);
        }
    }
};
class Drive{
    List<String> RootName;
    List<String> subFolder;
    List<String> subFiles;
    double totalSpace;
    double freeSpace;
    Drive() throws IOException{
        RootName = new ArrayList<String>();
        String s[] = null;
        s = this.details_about_disk_drive(false);
        for (String ss:s){
            if(ss!=null)
            RootName.add(ss);
        }
        for (String ss:RootName){
            System.out.println(ss);
        }
        System.out.println("total Space = " + totalSpace);
        System.out.println("free Space = " + freeSpace);
    }
    
        public String[] details_about_disk_drive(boolean printt)  throws IOException {
        // TODO code application logic here
        try {   
            int j = 0;
            String[] s = new String[12];
            File[] drives = File.listRoots();
            if (drives != null && drives.length > 0) {
                for (int i = 0; i<drives.length; i++) {
                    if (!drives[i].exists())continue;
                    s[j] = drives[i].getAbsolutePath();
                    totalSpace = drives[i].getTotalSpace();
                    freeSpace = drives[i].getFreeSpace();
                    if(printt == true){
                    System.out.println("Drive Letter: " + drives[i]);
                    System.out.println("\tTotal space: " + drives[i].getTotalSpace());
                    System.out.println("\tFree space: " + drives[i].getFreeSpace());
                    System.out.println();
                    }
                    j++;
                }
            }
            return s;
        }catch(Exception e) {
            // if any error occurs
            e.printStackTrace();
        }
        return null;
    }
                  /************ Question 2 *************/
      public void list_TheEmbded_FileOrFolder_OfDiskDrive()  throws IOException {
          Folder folder = null;
        File file = null;
        String[] f = null;
        int drive_count = 0;
        try {   
            int j = 0;
            String[] s = new String[12];
            File[] drives = File.listRoots();
            if (drives != null && drives.length > 0) {
                for (int i = 0; i<drives.length; i++) {
                    if (!drives[i].exists())continue;
                    s[j] = drives[i].getAbsolutePath();
                    
                    j++;
                }
            }
            for(String ss:s){
                if(ss!=null){
                    folder = new Folder(ss);
                    folder.print_the_embded_filesandfolder();
                }
            }
       
        }catch(Exception e) {
            // if any error occurs
            e.printStackTrace();
        }
    }
};
public class JavaApplication3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, Exception {
        // TODO code application logic here
        Folder folder = new Folder("C://Me/w");
        //folder.print_the_embded_filesandfolder();
        //folder.print_the_embded_filesandfolder();
        //folder.searchAbout_File_or_Folder("E://", "ww");
        //folder.print_the_embded_filesandfolder_asNameOrSize(1);
        //folder.print_the_embded_filesandfolder_asNameOrSize(2);
        //folder.delete_File_Or_Folder("C://Me/w/a");
        //folder.create_New_FileOrFolder("C://Me/w", "aaaaaa");
        FILE file = new FILE("C://Me/w/b.txt");
        //file.create_New_FileOrFolder("C://Me/w", "wwwwwwww.mp4");
        //file.delete_File_Or_Folder("C://Me/w/wwwwwwww.mp4");
        Drive drive = new Drive();
        //drive.details_about_disk_drive(true);
        //drive.list_TheEmbded_FileOrFolder_OfDiskDrive();
        //folder.list_of_embded_FileOrFolder("C://");
        
    }
    
}
