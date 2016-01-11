package com.mcnedward.app.pg.ingsw;

import java.io.InputStream;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Properties;
import java.util.Random;

import com.mcnedward.app.pg.utils.PropUtils;
import com.mcnedward.app.pg.utils.prob.ProbabilityDistribution;

public class ProblemGenerator
{
  public static final String TASK_NUMBER = "task.number";
  public static final String TASK_NUMBER_PARAM = "task.number.parameter.";
  public static final String TASK_COST = "task.cost";
  public static final String TASK_COST_PARAM = "task.cost.parameter.";
  public static final String TASK_SKILL = "task.skill";
  public static final String TASK_SKILL_PARAM = "task.skill.parameter.";
  public static final String GRAPH_EVRATE = "graph.e-v-rate";
  public static final String GRAPH_EVRATE_PARAM = "graph.e-v-rate.parameter.";
  public static final String EMPLOYEE_NUMBER = "employee.number";
  public static final String EMPLOYEE_NUMBER_PARAM = "employee.number.parameter.";
  public static final String EMPLOYEE_SALARY = "employee.salary";
  public static final String EMPLOYEE_SALARY_PARAM = "employee.salary.parameter.";
  public static final String EMPLOYEE_SKILL = "employee.skill";
  public static final String EMPLOYEE_SKILL_PARAM = "employee.skill.parameter.";
  public static final String SKILL_NUMBER = "skill.number";
  public static final String SKILL_NUMBER_PARAM = "skill.number.parameter.";
  
  public static class Task
  {
    public double cost;
    public ProblemGenerator.Skill[] skills;
    public static final String COST = "cost";
    public static final String SKILL_NUMBER = "skill.number";
    public static final String SKILL_PREFIX = "skill.";
    
    public Properties toProperties()
    {
      Properties localProperties = new Properties();
      
      localProperties.setProperty("cost", String.valueOf(this.cost));
      localProperties.setProperty("skill.number", String.valueOf(this.skills.length));
      for (int i = 0; i < this.skills.length; i++) {
        localProperties.setProperty("skill." + i, String.valueOf(this.skills[i].id));
      }
      return localProperties;
    }
    
    public void fromProperties(Properties paramProperties, ProblemGenerator.Skill[] paramArrayOfSkill)
    {
      this.cost = Double.parseDouble(paramProperties.getProperty("cost"));
      this.skills = new ProblemGenerator.Skill[Integer.parseInt(paramProperties.getProperty("skill.number"))];
      for (int i = 0; i < this.skills.length; i++) {
        this.skills[i] = paramArrayOfSkill[Integer.parseInt(paramProperties.getProperty("skill." + i))];
      }
    }
    
    public String toString()
    {
      String str = "";
      
      str = str + "T[Cost:" + this.cost + " Skills:[";
      for (int i = 0; i < this.skills.length; i++)
      {
        str = str + this.skills[i].toString();
        if (i < this.skills.length - 1) {
          str = str + ',';
        }
      }
      str = str + "]]";
      
      return str;
    }
  }
  
  @SuppressWarnings("rawtypes")
public static class Skill
    implements Comparable
  {
    public int id;
    public static final String ID = "id";
    
    public Skill(int paramInt)
    {
      this.id = paramInt;
    }
    
    public int compareTo(Object paramObject)
    {
      Skill localSkill = (Skill)paramObject;
      if (this.id < localSkill.id) {
        return -1;
      }
      if (this.id > localSkill.id) {
        return 1;
      }
      return 0;
    }
    
    public Properties toProperties()
    {
      Properties localProperties = new Properties();
      
      localProperties.setProperty("id", String.valueOf(this.id));
      
      return localProperties;
    }
    
    public String toString()
    {
      return "S" + this.id;
    }
  }
  
  public static class Employee
  {
    public double salary;
    public ProblemGenerator.Skill[] skills;
    int i;
    public static final String SALARY = "salary";
    public static final String SKILL_NUMBER = "skill.number";
    public static final String SKILL_PREFIX = "skill.";
    
    public Properties toProperties()
    {
      Properties localProperties = new Properties();
      
      localProperties.setProperty("salary", String.valueOf(this.salary));
      localProperties.setProperty("skill.number", String.valueOf(this.skills.length));
      for (this.i = 0; this.i < this.skills.length; this.i += 1) {
        localProperties.setProperty("skill." + this.i, String.valueOf(this.skills[this.i].id));
      }
      return localProperties;
    }
    
    public void fromProperties(Properties paramProperties, ProblemGenerator.Skill[] paramArrayOfSkill)
    {
      this.salary = Double.parseDouble(paramProperties.getProperty("salary"));
      this.skills = new ProblemGenerator.Skill[Integer.parseInt(paramProperties.getProperty("skill.number"))];
      for (int j = 0; j < this.skills.length; j++) {
        this.skills[j] = paramArrayOfSkill[Integer.parseInt(paramProperties.getProperty("skill." + j))];
      }
    }
    
    public String toString()
    {
      String str = "";
      
      str = str + "E[Salary:" + this.salary + " Skills:[";
      for (int j = 0; j < this.skills.length; j++)
      {
        str = str + this.skills[j].toString();
        if (j < this.skills.length - 1) {
          str = str + ',';
        }
      }
      str = str + "]]";
      
      return str;
    }
  }
  
  public static class DirectedGraph
  {
    public Object[] vertexes;
    public boolean[][] edges;
    public static final String ARC_NUMBER = "graph.arc.number";
    public static final String ARC_PREFIX = "graph.arc.";
    
    public Properties toProperties()
    {
      Properties localProperties = new Properties();
      int i = 0;
      for (int j = 0; j < this.edges.length; j++) {
        for (int k = 0; k < this.edges[j].length; k++) {
          if (this.edges[j][k] != false)
          {
            localProperties.setProperty("graph.arc." + i, String.valueOf(k) + " " + String.valueOf(j));
            
            i++;
          }
        }
      }
      localProperties.setProperty("graph.arc.number", String.valueOf(i));
      
      return localProperties;
    }
    
    public String toString()
    {
      String str = "";
      
      str = str + "Vertexes\n";
      for (int i = 0; i < this.vertexes.length; i++) {
        str = str + "V[" + i + "]:" + this.vertexes[i].toString() + "\n";
      }
      str = str + "Edges\n";
      for (int i = 0; i < this.edges.length; i++) {
        for (int j = 0; j < this.edges[i].length; j++) {
          if (this.edges[i][j] != false) {
            str = str + j + "->" + i + " ";
          }
        }
      }
      return str;
    }
    
    public void fromProperties(Properties paramProperties, Object[] paramArrayOfObject)
    {
      this.vertexes = paramArrayOfObject;
      int i = Integer.parseInt(paramProperties.getProperty("graph.arc.number"));
      
      this.edges = new boolean[paramArrayOfObject.length][];
      for (int j = 0; j < this.edges.length; j++)
      {
        this.edges[j] = new boolean[j];
        for (int k = 0; k < this.edges[j].length; k++) {
          this.edges[j][k] = false;
        }
      }
      for (int j = 0; j < i; j++)
      {
        String str1 = paramProperties.getProperty("graph.arc." + j);
        int m = str1.indexOf(' ');
        
        String str2 = str1.substring(0, m).trim();
        int n = Integer.parseInt(str2);
        
        str2 = str1.substring(m).trim();
        int i1 = Integer.parseInt(str2);
        
        this.edges[i1][n] = true;
      }
    }
  }
  
  public static class ProblemInstance
  {
    public ProblemGenerator.Skill[] skills;
    public ProblemGenerator.Task[] tasks;
    public ProblemGenerator.Employee[] emps;
    public ProblemGenerator.DirectedGraph graph;
    public static final String TASK_NUMBER = "task.number";
    public static final String TASK_PREFIX = "task.";
    public static final String SKILL_NUMBER = "skill.number";
    public static final String EMPLOYEE_NUMBER = "employee.number";
    public static final String EMPLOYEE_PREFIX = "employee.";
    
    private static void calculateIndex(int paramInt, int[] paramArrayOfInt)
    {
      paramArrayOfInt[0] = 1;
      paramArrayOfInt[1] = (paramInt - paramArrayOfInt[0] * (paramArrayOfInt[0] - 1) / 2);
      while (paramArrayOfInt[1] >= paramArrayOfInt[0])
      {
        paramArrayOfInt[0] += 1;
        paramArrayOfInt[1] = (paramInt - paramArrayOfInt[0] * (paramArrayOfInt[0] - 1) / 2);
      }
    }
    
    public static ProblemInstance generateInstance(Properties paramProperties)
    {
      ProblemInstance localProblemInstance = new ProblemInstance();
      Random localRandom = new Random();
      
      Properties localProperties = PropUtils.getPropertiesWithPrefix(paramProperties, "skill.number.parameter.");
      localProperties.setProperty("name", paramProperties.getProperty("skill.number"));
      
      ProbabilityDistribution localProbabilityDistribution1 = ProbabilityDistribution.createProbabilityDistribution(localProperties);
      
      localProblemInstance.skills = new ProblemGenerator.Skill[(int)localProbabilityDistribution1.nextValue()];
      for (int i = 0; i < localProblemInstance.skills.length; i++) {
        localProblemInstance.skills[i] = new ProblemGenerator.Skill(i);
      }
      localProperties = PropUtils.getPropertiesWithPrefix(paramProperties, "task.number.parameter.");
      localProperties.setProperty("name", paramProperties.getProperty("task.number"));
      localProbabilityDistribution1 = ProbabilityDistribution.createProbabilityDistribution(localProperties);
      
      localProblemInstance.tasks = new ProblemGenerator.Task[(int)localProbabilityDistribution1.nextValue()];
      
      localProperties = PropUtils.getPropertiesWithPrefix(paramProperties, "task.cost.parameter.");
      localProperties.setProperty("name", paramProperties.getProperty("task.cost"));
      ProbabilityDistribution localProbabilityDistribution2 = ProbabilityDistribution.createProbabilityDistribution(localProperties);
      
      localProperties = PropUtils.getPropertiesWithPrefix(paramProperties, "task.skill.parameter.");
      localProperties.setProperty("name", paramProperties.getProperty("task.skill"));
      ProbabilityDistribution localProbabilityDistribution3 = ProbabilityDistribution.createProbabilityDistribution(localProperties);
      int j;
      for (int i = 0; i < localProblemInstance.tasks.length; i++)
      {
        localProblemInstance.tasks[i] = new ProblemGenerator.Task();
        
        localProblemInstance.tasks[i].cost = localProbabilityDistribution2.nextValue();
        localProblemInstance.tasks[i].skills = new ProblemGenerator.Skill[(int)localProbabilityDistribution3.nextValue()];
        for (j = 0; j < localProblemInstance.tasks[i].skills.length; j++) {
          localProblemInstance.tasks[i].skills[j] = localProblemInstance.skills[localRandom.nextInt(localProblemInstance.skills.length)];
        }
        Arrays.sort(localProblemInstance.tasks[i].skills);
        
        ProblemGenerator.Skill localSkill1 = null;
        int m = 0;
        for (j = 0; j < localProblemInstance.tasks[i].skills.length; j++) {
          if (localProblemInstance.tasks[i].skills[j] == localSkill1)
          {
            localProblemInstance.tasks[i].skills[j] = null;
          }
          else
          {
            localSkill1 = localProblemInstance.tasks[i].skills[j];
            m++;
          }
        }
        Skill[] localObject = new ProblemGenerator.Skill[m];
        m = 0;
        for (j = 0; j < localProblemInstance.tasks[i].skills.length; j++) {
          if (localProblemInstance.tasks[i].skills[j] != null) {
            localObject[(m++)] = localProblemInstance.tasks[i].skills[j];
          }
        }
        localProblemInstance.tasks[i].skills = ((ProblemGenerator.Skill[])localObject);
      }
      localProperties = PropUtils.getPropertiesWithPrefix(paramProperties, "graph.e-v-rate.parameter.");
      localProperties.setProperty("name", paramProperties.getProperty("graph.e-v-rate"));
      localProbabilityDistribution1 = ProbabilityDistribution.createProbabilityDistribution(localProperties);
      
      localProblemInstance.graph = new ProblemGenerator.DirectedGraph();
      localProblemInstance.graph.vertexes = localProblemInstance.tasks;
      localProblemInstance.graph.edges = new boolean[localProblemInstance.graph.vertexes.length][];
      for (int i = 0; i < localProblemInstance.graph.edges.length; i++)
      {
        localProblemInstance.graph.edges[i] = new boolean[i];
        for (j = 0; j < localProblemInstance.graph.edges[i].length; j++) {
          localProblemInstance.graph.edges[i][j] = false;
        }
      }
      int k = (int)(localProbabilityDistribution1.nextValue() * localProblemInstance.tasks.length);
      int m = localProblemInstance.graph.vertexes.length;
      int[] localObject = new int[2];
      
      m = m * (m - 1) / 2;
      for (; k > 0; k--)
      {
        calculateIndex(localRandom.nextInt(m), (int[])localObject);
        localProblemInstance.graph.edges[localObject[0]][localObject[1]] = true;
      }
      localProperties = PropUtils.getPropertiesWithPrefix(paramProperties, "employee.number.parameter.");
      localProperties.setProperty("name", paramProperties.getProperty("employee.number"));
      localProbabilityDistribution1 = ProbabilityDistribution.createProbabilityDistribution(localProperties);
      
      localProblemInstance.emps = new ProblemGenerator.Employee[(int)localProbabilityDistribution1.nextValue()];
      
      localProperties = PropUtils.getPropertiesWithPrefix(paramProperties, "employee.salary.parameter.");
      localProperties.setProperty("name", paramProperties.getProperty("employee.salary"));
      ProbabilityDistribution localProbabilityDistribution4 = ProbabilityDistribution.createProbabilityDistribution(localProperties);
      
      localProperties = PropUtils.getPropertiesWithPrefix(paramProperties, "employee.skill.parameter.");
      localProperties.setProperty("name", paramProperties.getProperty("employee.skill"));
      localProbabilityDistribution3 = ProbabilityDistribution.createProbabilityDistribution(localProperties);
      for (int i = 0; i < localProblemInstance.emps.length; i++)
      {
        localProblemInstance.emps[i] = new ProblemGenerator.Employee();
        
        localProblemInstance.emps[i].salary = localProbabilityDistribution4.nextValue();
        localProblemInstance.emps[i].skills = new ProblemGenerator.Skill[(int)localProbabilityDistribution3.nextValue()];
        for (j = 0; j < localProblemInstance.emps[i].skills.length; j++) {
          localProblemInstance.emps[i].skills[j] = localProblemInstance.skills[localRandom.nextInt(localProblemInstance.skills.length)];
        }
        Arrays.sort(localProblemInstance.emps[i].skills);
        
        ProblemGenerator.Skill localSkill2 = null;
        int n = 0;
        for (j = 0; j < localProblemInstance.emps[i].skills.length; j++) {
          if (localProblemInstance.emps[i].skills[j] == localSkill2)
          {
            localProblemInstance.emps[i].skills[j] = null;
          }
          else
          {
            localSkill2 = localProblemInstance.emps[i].skills[j];
            n++;
          }
        }
        ProblemGenerator.Skill[] arrayOfSkill = new ProblemGenerator.Skill[n];
        n = 0;
        for (j = 0; j < localProblemInstance.emps[i].skills.length; j++) {
          if (localProblemInstance.emps[i].skills[j] != null) {
            arrayOfSkill[(n++)] = localProblemInstance.emps[i].skills[j];
          }
        }
        localProblemInstance.emps[i].skills = arrayOfSkill;
      }
      return localProblemInstance;
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public boolean validInstance()
    {
      HashSet localHashSet1 = new HashSet();
      HashSet localHashSet2 = new HashSet();
      int j;
      for (int i = 0; i < this.tasks.length; i++) {
        for (j = 0; j < this.tasks[i].skills.length; j++) {
          localHashSet1.add(this.tasks[i].skills[j]);
        }
      }
      for (int i = 0; i < this.emps.length; i++) {
        for (j = 0; j < this.emps[i].skills.length; j++) {
          localHashSet2.add(this.emps[i].skills[j]);
        }
      }
      return localHashSet2.containsAll(localHashSet1);
    }
    
    public Properties toProperties()
    {
      Properties localProperties1 = new Properties();
      
      localProperties1.setProperty("skill.number", String.valueOf(this.skills.length));
      
      localProperties1.setProperty("task.number", String.valueOf(this.tasks.length));
      Properties localProperties2;
      for (int i = 0; i < this.tasks.length; i++)
      {
        localProperties2 = this.tasks[i].toProperties();
        localProperties2 = PropUtils.putPrefixToProperties("task." + i + ".", localProperties2);
        localProperties1.putAll(localProperties2);
      }
      localProperties1.setProperty("employee.number", String.valueOf(this.emps.length));
      for (int i = 0; i < this.emps.length; i++)
      {
        localProperties2 = this.emps[i].toProperties();
        localProperties2 = PropUtils.putPrefixToProperties("employee." + i + ".", localProperties2);
        localProperties1.putAll(localProperties2);
      }
      localProperties1.putAll(this.graph.toProperties());
      
      return localProperties1;
    }
    
    public void fromProperties(Properties paramProperties)
    {
      this.skills = new ProblemGenerator.Skill[Integer.parseInt(paramProperties.getProperty("skill.number"))];
      for (int i = 0; i < this.skills.length; i++) {
        this.skills[i] = new ProblemGenerator.Skill(i);
      }
      this.tasks = new ProblemGenerator.Task[Integer.parseInt(paramProperties.getProperty("task.number"))];
      Properties localProperties;
      for (int i = 0; i < this.tasks.length; i++)
      {
        localProperties = PropUtils.getPropertiesWithPrefix(paramProperties, "task." + i + ".");
        this.tasks[i] = new ProblemGenerator.Task();
        this.tasks[i].fromProperties(localProperties, this.skills);
      }
      this.emps = new ProblemGenerator.Employee[Integer.parseInt(paramProperties.getProperty("employee.number"))];
      for (int i = 0; i < this.emps.length; i++)
      {
        localProperties = PropUtils.getPropertiesWithPrefix(paramProperties, "employee." + i + ".");
        this.emps[i] = new ProblemGenerator.Employee();
        this.emps[i].fromProperties(localProperties, this.skills);
      }
      this.graph = new ProblemGenerator.DirectedGraph();
      
      this.graph.fromProperties(paramProperties, this.tasks);
    }
    
    public String toString()
    {
      String str = "";
      
      str = str + "Skills:\n";
      for (int i = 0; i < this.skills.length; i++) {
        str = str + "  " + this.skills[i].toString() + "\n";
      }
      str = str + "Tasks:\n";
      for (int i = 0; i < this.tasks.length; i++) {
        str = str + "  " + this.tasks[i].toString() + "\n";
      }
      str = str + "Employees:\n";
      for (int i = 0; i < this.emps.length; i++) {
        str = str + "  " + this.emps[i].toString() + "\n";
      }
      str = str + "Graph\n";
      
      str = str + this.graph + "\n";
      if (validInstance()) {
        str = str + "Valid Instance\n";
      } else {
        str = str + "Invalid Instance\n";
      }
      return str;
    }
  }
  
  public Properties main(String[] paramArrayOfString)
    throws Exception
  {
    if (paramArrayOfString.length < 2)
    {
      System.out.println("Parameters: <generator config file> <output file>");
      return null;
    }
    Properties localProperties1 = new Properties();
    String file1 = paramArrayOfString[0];
    
	InputStream stream = getClass().getResourceAsStream(file1);
	if (stream == null) {
		throw new Exception("Problem creating stream...");
	}
	
	localProperties1.load(stream);
    
    ProblemInstance localProblemInstance = ProblemInstance.generateInstance(localProperties1);
    
    Properties localProperties2 = localProblemInstance.toProperties();
    
//    localProperties2.store(out, "Generated by ProblemGenerator [" + (localProblemInstance.validInstance() ? "Valid instance" : "Invalid instance") + "]");
    
    stream.close();
    return localProperties2;
  }
}
