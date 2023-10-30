package view;

import Dao.Empresa;
import IO.Teclado;
import IO.TecladoTemplate;
import Model.Departamento;
import Model.Empleado;
import SQL.BD;
import SQL.EmpresaSQL;

import java.time.LocalDate;
import java.util.ArrayList;


public class App {

    public static void main(String[] args) {
        EmpresaSQL e = new EmpresaSQL();
        System.out.println("1.Crear,2.Modificar,3.Eliminar,4.Mostrar,0.Salir");
        int option = TecladoTemplate.leerEntero("Introduce una de las opciones");
        switch (option) {
            case 1:
                String create = Teclado.leerString("¿Quieres crear (D)epartamento u (E)mpleado?");
                ArrayList<Object> lista;
                if (create.equalsIgnoreCase("D")) {
                    lista = Empresa.selectAllFromTable(BD.getConnection(), "departamentos");
                    for (Object element : lista) {
                        Departamento mostrar = (Departamento) element;
                        System.out.println(lista.indexOf(element) + ". " + mostrar.toString());
                    }
                    String nombre = Teclado.leerString("Introduce un nombre para el nuevo departamento");
                    lista = Empresa.selectAllFromTable(BD.getConnection(), "empleados");
                    for (Object element : lista) {
                        Empleado mostrar = (Empleado) element;
                        System.out.println(lista.indexOf(element) + ". " + mostrar.toString());
                    }
                    int seleccionaId = Teclado.leerEntero("Introduce el numero asociado al empleado que desees que sea jefe de departamento", 0, lista.size()-1);
                    Departamento n1 = new Departamento();
                    n1.setNombre(nombre);
                    n1.setJefe((Empleado) lista.get(seleccionaId));
                    Empresa.insertDepartamento(BD.getConnection(), n1);
                } else if (create.equalsIgnoreCase("e")) {
                    lista = Empresa.selectAllFromTable(BD.getConnection(), "empleados");
                    for (Object element : lista) {
                        Empleado mostrar = (Empleado) element;
                        System.out.println(lista.indexOf(element) + ". " + mostrar.toString());
                    }
                    String nombre = Teclado.leerString("Introduce un nombre para el nuevo Empleado");
                    Double salario = Teclado.leerDouble("Introduce el salario del empleado");
                    LocalDate nacimiento = Teclado.leerLocalDate("Introduce la fecha de nacimiento");
                    lista = Empresa.selectAllFromTable(BD.getConnection(), "departamentos");
                    for (Object element : lista) {
                        Departamento mostrar = (Departamento) element;
                        System.out.println(lista.indexOf(element) + ". " + mostrar.toString());
                    }
                    int seleccionaId = Teclado.leerEntero("Introduce el numero asociado al departamento al que se quiera asignar el empleado", 0, lista.size()-1);
                    Empleado n1 = new Empleado();
                    n1.setNombre(nombre);
                    n1.setSalario(salario);
                    n1.setNacido(nacimiento);
                    n1.setDepartamento((Departamento) lista.get(seleccionaId));
                    Empresa.insertEmpleado(BD.getConnection(), n1);


                } else {
                    System.out.println("Atras...");

                }

                break;
            case 2:


                String modifi = Teclado.leerString("¿Quieres Modificar (D)epartamento u (E)mpleado?");
                if (modifi.equalsIgnoreCase("D")) {
                    lista = Empresa.selectAllFromTable(BD.getConnection(), "departamentos");
                    for (Object element : lista) {
                        Departamento mostrar = (Departamento) element;
                        System.out.println(lista.indexOf(element) + ". " + mostrar.toString());
                    }
                    int seleccionaId = Teclado.leerEntero("Introduce el numero asociado al departamento que desees Modificar", 0, lista.size()-1);
                    Departamento mod = (Departamento) lista.get(seleccionaId);
                    System.out.println(mod.toString());
                    String campo = Teclado.leerString("Introduce el elemento a modificar[(N)ombre,(J)efe]");
                    if (campo.equalsIgnoreCase("nombre") || campo.equalsIgnoreCase("n")) {
                        System.out.println(mod.getNombre());
                        String modificar = Teclado.leerString("Introduce el nuevo " + campo);
                        mod.setNombre(modificar);
                    } else if (campo.equalsIgnoreCase("jefe") || campo.equalsIgnoreCase("j")) {
                        System.out.println(mod.getJefe());
                        lista = Empresa.selectAllFromTable(BD.getConnection(), "empleados");
                        for (Object element : lista) {
                            Empleado mostrar = (Empleado) element;
                            System.out.println(lista.indexOf(element) + ". " + mostrar.toString());
                        }
                        seleccionaId = Teclado.leerEntero("Introduce el numero asociado al empleado que desees que sea jefe de departamento", 0, lista.size()-1);

                        mod.setJefe((Empleado) lista.get(seleccionaId));
                    } else {
                        System.out.println("Atras...");
                        break;
                    }
                    Empresa.updateDepartamentos(BD.getConnection(), mod);
                } else {

                    lista = Empresa.selectAllFromTable(BD.getConnection(), "empleados");
                    for (Object element : lista) {
                        Empleado mostrar = (Empleado) element;
                        System.out.println(lista.indexOf(element) + ". " + mostrar.toString());
                    }
                    int seleccionaId = Teclado.leerEntero("Introduce el numero asociado al Empleado que desees Modificar", 0, lista.size()-1);
                    Empleado mod = (Empleado) lista.get(seleccionaId);
                    System.out.println(mod.toString());
                    String campo = Teclado.leerString("Introduce el elemento a modificar(Nombre,Salario,Nacimineto,Departamento)");
                    if (campo.equalsIgnoreCase("nombre") || campo.equalsIgnoreCase("n")) {
                        System.out.println(mod.getNombre());
                        String modificar = Teclado.leerString("Introduce el nuevo " + campo);
                        mod.setNombre(modificar);
                    } else if (campo.equalsIgnoreCase("departamento") || campo.equalsIgnoreCase("d")) {
                        System.out.println(mod.getDepartamento());
                        seleccionaId = Teclado.leerEntero("Introduce el numero asociado al  departamento que desees remplazar", 0, lista.size()-1);
                        lista = Empresa.selectAllFromTable(BD.getConnection(), "departamentos");
                        for (Object element : lista) {
                            Departamento mostrar = (Departamento) element;
                            System.out.println(lista.indexOf(element) + ". " + mostrar.toString());
                        }
                        mod.setDepartamento((Departamento) lista.get(seleccionaId));
                    } else if (campo.equalsIgnoreCase("salario") || campo.equalsIgnoreCase("s")) {
                        System.out.println(mod.getSalario());
                        Double modificar = Teclado.leerDouble("Introduce el nuevo " + campo);
                        mod.setSalario(modificar);
                    } else if (campo.equalsIgnoreCase("nacimiento") || campo.equalsIgnoreCase("na")) {
                        System.out.println(mod.getNacido());
                        LocalDate modificar = Teclado.leerLocalDate("Introduce el nuevo " + campo);
                        mod.setNacido(modificar);
                    } else {
                        System.out.println("Atras...");
                        break;
                    }


                    Empresa.updateEmpleado(BD.getConnection(), mod);

                }


                break;
            case 3:
                String eli  = Teclado.leerString("¿Quieres Eliminar (D)epartamento u (E)mpleado?");;
                if (eli.equalsIgnoreCase("D")) {
                    lista = Empresa.selectAllFromTable(BD.getConnection(), "departamentos");
                    for (Object element : lista) {
                        Departamento mostrar = (Departamento) element;
                        System.out.println(lista.indexOf(element) + ". " + mostrar.toString());
                    }
                    Departamento n1 = (Departamento) lista.get(Teclado.leerEntero("Introduce el numero asociado al Departamento que desees que sea jefe de departamento", 0, lista.size()-1));
                    Empresa.eliminarFromTable(BD.getConnection(), "departamentos", "empleados", n1.getId());
                } else if (eli.equalsIgnoreCase("e")) {
                    lista = Empresa.selectAllFromTable(BD.getConnection(), "empleados");
                    for (Object element : lista) {
                        Empleado mostrar = (Empleado) element;
                        System.out.println(lista.indexOf(element) + ". " + mostrar.toString());
                    }
                    Empleado n1 = (Empleado) lista.get(Teclado.leerEntero("Introduce el numero asociado al Empleado que desees que sea jefe de departamento", 0, lista.size() - 1));
                    Empresa.eliminarFromTable(BD.getConnection(), "empleados", "departamentos", n1.getId());
                }
                    break;
                    case 4:
                        String most  = Teclado.leerString("¿Quieres Mostrar (D)epartamento u (E)mpleado?");;
                        if(most.equalsIgnoreCase("D")){
                            lista = Empresa.selectAllFromTable(BD.getConnection(), "departamentos");
                            for (Object element : lista) {
                                Departamento mostrar = (Departamento) element;
                                System.out.println(lista.indexOf(element) + ". " + mostrar.toString());
                            }
                        } else if (most.equalsIgnoreCase("e")) {
                            lista = Empresa.selectAllFromTable(BD.getConnection(), "empleados");
                            for (Object element : lista) {
                                Empleado mostrar = (Empleado) element;
                                System.out.println(lista.indexOf(element) + ". " + mostrar.toString());
                            }
                        }
                        break;
                        }


                }
        }



