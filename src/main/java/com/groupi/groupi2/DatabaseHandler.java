package com.groupi.groupi2;

import java.sql.*;

public class DatabaseHandler {

    private static String url = "jdbc:sqlite:jss.db";
    private static Connection conn = null;


    /**
     * Creates a new SQLite database named jss.db in the current directory if it does not already exist.
     * @throws SQLException on error
     */
    public static void createNewDatabase() {
        try {
            Connection conn = DriverManager.getConnection(url);
            if (conn != null) {
                System.out.println("Database established.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Creates a new SQLite table if it does not already exist
     * @throws SQLException on error
     */
    public static void createSeekersTable() {
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS seekers (\n"
                + " id integer PRIMARY KEY,\n"
                + " firstName text NOT NULL,\n"
                + " lastName text NOT NULL,\n"
                + " email text NOT NULL,\n"
                + " locationId integer,\n"
                + " education text,\n"
                + " skillIds text,\n"
                + " password text NOT NULL\n"
                + ");";

        try{
            Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void createRecruitersTable() {
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS recruiters (\n"
                + " id integer PRIMARY KEY,\n"
                + " firstName text NOT NULL,\n"
                + " lastName text NOT NULL,\n"
                + " email text NOT NULL,\n"
                + " password text NOT NULL,\n"
                + " companyName text,\n"
                + " locationId integer,\n"
                + " abn text\n"
                + ");";

        try{
            Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Creates a new SQLite table for storing Jobs, if it does not already exist.
     * @throws SQLException on error
     */
    public static void createJobsTable()
    {
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS jobs (\n"
                + " id integer PRIMARY KEY,\n"
                + " recruiterId integer NOT NULL,\n"
                + " title text NOT NULL,\n"
                + " description text NOT NULL,\n"
                + " salaryRange text NOT NULL,\n"
                + " locationId integer NOT NULL,\n"
                + " categoryIds text NOT NULL,\n"
                + " isPublished integer NOT NULL,\n"
                + " isDisabled integer DEFAULT '0' NOT NULL,\n"
                + " skillIds text NOT NULL\n"
                + ");";

        try{
            Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    /**
     * Creates a new SQLite table for storing Jobs, if it does not already exist.
     * @throws SQLException on error
     */
    public static void createJobApplicationsTable()
    {
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS job_applications (\n"
                + " id integer PRIMARY KEY,\n"
                + " jobId integer NOT NULL,\n"
                + " jobSeekerId integer NOT NULL,\n"
                + " coverLetter text DEFAULT '' NOT NULL,\n"
                + " additionalInfo text DEFAULT '' NOT NULL\n"
                + ");";

        try{
            Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Creates a new SQLite table fpr storing Job locations, if it does not already exist.
     * @throws SQLException on error
     */
    public static void createLocationsTable()
    {
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS locations (id integer PRIMARY KEY, name text NOT NULL);";

        try{
            Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Creates a new SQLite table fpr storing Job Skills, if it does not already exist.
     * @throws SQLException on error
     */
    public static void createSkillsTable()
    {
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS skills (id integer PRIMARY KEY, name text NOT NULL);";

        try{
            Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Creates a new SQLite table fpr storing Job Categories, if it does not already exist.
     * @throws SQLException on error
     */
    public static void createCategoriesTable()
    {
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS categories (id integer PRIMARY KEY, name text NOT NULL);";

        try{
            Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Store Job Locations in the system
     *
     */
    public void storeLocations()
    {

        try{
            Connection conn = new DatabaseHandler().getConn();
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery("SELECT COUNT(*) as count FROM locations");
            boolean hasBeenInitialised = result.getInt("count") > 0;
            if (!hasBeenInitialised) {
                // insert the data
                statement.executeUpdate("INSERT INTO locations(name) " + "VALUES ('New South Wales');");
                statement.executeUpdate("INSERT INTO locations(name) " + "VALUES ('Victoria');");
                statement.executeUpdate("INSERT INTO locations(name) " + "VALUES ('Queensland');");
                statement.executeUpdate("INSERT INTO locations(name) " + "VALUES ('Western Australia');");
                statement.executeUpdate("INSERT INTO locations(name) " + "VALUES ('Tasmania');");
                statement.executeUpdate("INSERT INTO locations(name) " + "VALUES ('Australian Capital Territory');");
                statement.executeUpdate("INSERT INTO locations(name) " + "VALUES ('Northern Territory');");
                statement.executeUpdate("INSERT INTO locations(name) " + "VALUES ('Remote');");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Store Job Skills in the system
     *
     */
    public void storeSkills()
    {

        try{
            Connection conn = new DatabaseHandler().getConn();
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery("SELECT COUNT(*) as count FROM skills");
            boolean hasBeenInitialised = result.getInt("count") > 0;
            if (!hasBeenInitialised) {
                // insert the data
                statement.executeUpdate("INSERT INTO skills(name) " + "VALUES ('Xero');");
                statement.executeUpdate("INSERT INTO skills(name) " + "VALUES ('Powerpoint');");
                statement.executeUpdate("INSERT INTO skills(name) " + "VALUES ('Contact Centre experience');");
                statement.executeUpdate("INSERT INTO skills(name) " + "VALUES ('Mail Chimp');");
                statement.executeUpdate("INSERT INTO skills(name) " + "VALUES ('Adobe Illustrator');");
                statement.executeUpdate("INSERT INTO skills(name) " + "VALUES ('Loan products management');");
                statement.executeUpdate("INSERT INTO skills(name) " + "VALUES ('Face to face customer service');");
                statement.executeUpdate("INSERT INTO skills(name) " + "VALUES ('Financial Planning');");
                statement.executeUpdate("INSERT INTO skills(name) " + "VALUES ('People Management');");
                statement.executeUpdate("INSERT INTO skills(name) " + "VALUES ('Leading change projects');");
                statement.executeUpdate("INSERT INTO skills(name) " + "VALUES ('Centrelink benefits management');");
                statement.executeUpdate("INSERT INTO skills(name) " + "VALUES ('Pipes installation');");
                statement.executeUpdate("INSERT INTO skills(name) " + "VALUES ('Connect disconnect A/C');");
                statement.executeUpdate("INSERT INTO skills(name) " + "VALUES ('Installing ADSL cabling');");
                statement.executeUpdate("INSERT INTO skills(name) " + "VALUES ('Installing electrical cabling');");
                statement.executeUpdate("INSERT INTO skills(name) " + "VALUES ('Consulting retail clients');");
                statement.executeUpdate("INSERT INTO skills(name) " + "VALUES ('Crop irrigation');");
                statement.executeUpdate("INSERT INTO skills(name) " + "VALUES ('Java programming');");
                statement.executeUpdate("INSERT INTO skills(name) " + "VALUES ('Python programming');");
                statement.executeUpdate("INSERT INTO skills(name) " + "VALUES ('Superannuation Management ');");
                statement.executeUpdate("INSERT INTO skills(name) " + "VALUES ('Litigation');");
                statement.executeUpdate("INSERT INTO skills(name) " + "VALUES ('Criminal Case Defence');");
                statement.executeUpdate("INSERT INTO skills(name) " + "VALUES ('Inheritance and Property case management');");
                statement.executeUpdate("INSERT INTO skills(name) " + "VALUES ('Driving / operating forklifts');");
                statement.executeUpdate("INSERT INTO skills(name) " + "VALUES ('Driving / operating industrial cutters');");
                statement.executeUpdate("INSERT INTO skills(name) " + "VALUES ('End of day cashier management');");
                statement.executeUpdate("INSERT INTO skills(name) " + "VALUES ('Programming language (R, Python, Scala, Matlab)');");
                statement.executeUpdate("INSERT INTO skills(name) " + "VALUES ('Business Process Modeling');");
                statement.executeUpdate("INSERT INTO skills(name) " + "VALUES ('Technical and non-technical communication');");
                statement.executeUpdate("INSERT INTO skills(name) " + "VALUES ('Excel');");
                statement.executeUpdate("INSERT INTO skills(name) " + "VALUES ('SQL');");
                statement.executeUpdate("INSERT INTO skills(name) " + "VALUES ('STATA, SPSS, SAS');");
                statement.executeUpdate("INSERT INTO skills(name) " + "VALUES ('Microsoft Visio');");
                statement.executeUpdate("INSERT INTO skills(name) " + "VALUES ('Machine learning');");
                statement.executeUpdate("INSERT INTO skills(name) " + "VALUES ('Agile Business Analysis');");
                statement.executeUpdate("INSERT INTO skills(name) " + "VALUES ('Time management');");
                statement.executeUpdate("INSERT INTO skills(name) " + "VALUES ('Effective communication');");
                statement.executeUpdate("INSERT INTO skills(name) " + "VALUES ('Emotional intelligence');");
                statement.executeUpdate("INSERT INTO skills(name) " + "VALUES ('Conflict management');");
                statement.executeUpdate("INSERT INTO skills(name) " + "VALUES ('Teamwork skills');");
                statement.executeUpdate("INSERT INTO skills(name) " + "VALUES ('Stress management');");
                statement.executeUpdate("INSERT INTO skills(name) " + "VALUES ('Problem-solving');");
                statement.executeUpdate("INSERT INTO skills(name) " + "VALUES ('Attention to detail');");
                statement.executeUpdate("INSERT INTO skills(name) " + "VALUES ('Productivity & organization');");
                statement.executeUpdate("INSERT INTO skills(name) " + "VALUES ('Critical thinking');");
                statement.executeUpdate("INSERT INTO skills(name) " + "VALUES ('Data analysis');");
                statement.executeUpdate("INSERT INTO skills(name) " + "VALUES ('SEO/SEM');");
                statement.executeUpdate("INSERT INTO skills(name) " + "VALUES ('Web analytics');");
                statement.executeUpdate("INSERT INTO skills(name) " + "VALUES ('HTML & CSS');");
                statement.executeUpdate("INSERT INTO skills(name) " + "VALUES ('Email marketing');");
                statement.executeUpdate("INSERT INTO skills(name) " + "VALUES ('CRO and A/B Testing');");
                statement.executeUpdate("INSERT INTO skills(name) " + "VALUES ('Data visualization & pattern-finding through critical thinking');");
                statement.executeUpdate("INSERT INTO skills(name) " + "VALUES ('Project/campaign management');");
                statement.executeUpdate("INSERT INTO skills(name) " + "VALUES ('B2B Marketing');");
                statement.executeUpdate("INSERT INTO skills(name) " + "VALUES ('Copyrighting');");
                statement.executeUpdate("INSERT INTO skills(name) " + "VALUES ('Storytelling');");
                statement.executeUpdate("INSERT INTO skills(name) " + "VALUES ('Sales');");
                statement.executeUpdate("INSERT INTO skills(name) " + "VALUES ('Task delegation');");
                statement.executeUpdate("INSERT INTO skills(name) " + "VALUES ('Proposal writing');");
                statement.executeUpdate("INSERT INTO skills(name) " + "VALUES ('Customer Relationship Management (CRM)');");
                statement.executeUpdate("INSERT INTO skills(name) " + "VALUES ('Buyer engagement');");
                statement.executeUpdate("INSERT INTO skills(name) " + "VALUES ('Teamwork');");
                statement.executeUpdate("INSERT INTO skills(name) " + "VALUES ('Infographics');");
                statement.executeUpdate("INSERT INTO skills(name) " + "VALUES ('Photo editing');");
                statement.executeUpdate("INSERT INTO skills(name) " + "VALUES ('Ad design');");
                statement.executeUpdate("INSERT INTO skills(name) " + "VALUES ('Anti Money Laundering');");
                statement.executeUpdate("INSERT INTO skills(name) " + "VALUES ('Microsoft Excel (Advanced)');");
                statement.executeUpdate("INSERT INTO skills(name) " + "VALUES ('Big Data Analysis & SQL');");
                statement.executeUpdate("INSERT INTO skills(name) " + "VALUES ('Cognos Analytics (IBM)');");
                statement.executeUpdate("INSERT INTO skills(name) " + "VALUES ('Javascript');");
                statement.executeUpdate("INSERT INTO skills(name) " + "VALUES ('Data Mapping');");
                statement.executeUpdate("INSERT INTO skills(name) " + "VALUES ('Physical endurance');");
                statement.executeUpdate("INSERT INTO skills(name) " + "VALUES ('Infection control');");
                statement.executeUpdate("INSERT INTO skills(name) " + "VALUES ('Surgery preparation');");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Store Job Categories in the system
     *
     */
    public void storeCategories()
    {

        try{
            Connection conn = new DatabaseHandler().getConn();
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery("SELECT COUNT(*) as count FROM categories");
            boolean hasBeenInitialised = result.getInt("count") > 0;
            if (!hasBeenInitialised) {
                // insert the data
                statement.executeUpdate("INSERT INTO categories(name) " + "VALUES ('Accounting');");
                statement.executeUpdate("INSERT INTO categories(name) " + "VALUES ('Administration & Office Support');");
                statement.executeUpdate("INSERT INTO categories(name) " + "VALUES ('Advertising, Arts & Media');");
                statement.executeUpdate("INSERT INTO categories(name) " + "VALUES ('Banking & Financial Services');");
                statement.executeUpdate("INSERT INTO categories(name) " + "VALUES ('Call Centre & Customer Service');");
                statement.executeUpdate("INSERT INTO categories(name) " + "VALUES ('CEO & General Management');");
                statement.executeUpdate("INSERT INTO categories(name) " + "VALUES ('Community Services & Development');");
                statement.executeUpdate("INSERT INTO categories(name) " + "VALUES ('Construction');");
                statement.executeUpdate("INSERT INTO categories(name) " + "VALUES ('Consulting & Strategy');");
                statement.executeUpdate("INSERT INTO categories(name) " + "VALUES ('Design & Architecture');");
                statement.executeUpdate("INSERT INTO categories(name) " + "VALUES ('Education & Training');");
                statement.executeUpdate("INSERT INTO categories(name) " + "VALUES ('Engineering');");
                statement.executeUpdate("INSERT INTO categories(name) " + "VALUES ('Farming, Animals & Conservation');");
                statement.executeUpdate("INSERT INTO categories(name) " + "VALUES ('Government & Defence');");
                statement.executeUpdate("INSERT INTO categories(name) " + "VALUES ('Healthcare & Medical');");
                statement.executeUpdate("INSERT INTO categories(name) " + "VALUES ('Hospitality & Tourism');");
                statement.executeUpdate("INSERT INTO categories(name) " + "VALUES ('Human Resources & Recruitment');");
                statement.executeUpdate("INSERT INTO categories(name) " + "VALUES ('Information & Communication Technology');");
                statement.executeUpdate("INSERT INTO categories(name) " + "VALUES ('Insurance & Superannuation');");
                statement.executeUpdate("INSERT INTO categories(name) " + "VALUES ('Legal');");
                statement.executeUpdate("INSERT INTO categories(name) " + "VALUES ('Manufacturing, Transport & Logistics');");
                statement.executeUpdate("INSERT INTO categories(name) " + "VALUES ('Marketing & Communications');");
                statement.executeUpdate("INSERT INTO categories(name) " + "VALUES ('Mining, Resources & Energy');");
                statement.executeUpdate("INSERT INTO categories(name) " + "VALUES ('Real Estate & Property');");
                statement.executeUpdate("INSERT INTO categories(name) " + "VALUES ('Retail & Consumer Products');");
                statement.executeUpdate("INSERT INTO categories(name) " + "VALUES ('Sales');");
                statement.executeUpdate("INSERT INTO categories(name) " + "VALUES ('Science & Technology');");
                statement.executeUpdate("INSERT INTO categories(name) " + "VALUES ('Science & Technology');");
                statement.executeUpdate("INSERT INTO categories(name) " + "VALUES ('Sport & Recreation');");
                statement.executeUpdate("INSERT INTO categories(name) " + "VALUES ('Trades & Services');");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    /**
     * Connects to the SQLite database
     * @return Connection
     * @throws SQLException on error
     *
     */
    public Connection getConn() throws SQLException {
        if (conn == null) {
            try {
                conn = DriverManager.getConnection(url);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return conn;
    }

    /**
     * Setup database with default tables and data.
     *
     */
    public void initialiseDatabase() {
        // Create database and tables
        createNewDatabase();
        createRecruitersTable();
        createSeekersTable();
        createJobsTable();
        createJobApplicationsTable();
        createLocationsTable();
        createSkillsTable();
        createCategoriesTable();

        // Create default data
        storeCategories();
        storeLocations();
        storeSkills();
    }
}

