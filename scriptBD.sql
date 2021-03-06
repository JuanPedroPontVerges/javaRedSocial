CREATE DATABASE [RedVecinalFinal]
GO
USE [RedVecinalFinal]
GO
/****** Object:  Table [dbo].[comentarios]    Script Date: 4/11/2020 16:33:40 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[comentarios](
	[id_comentario] [int] IDENTITY(1,1) NOT NULL,
	[id_usuario] [int] NOT NULL,
	[comentario] [varchar](150) NOT NULL,
	[id_comercio] [int] NOT NULL,
	[puntaje] [float] NULL,
	[id_respuesta] [int] NULL,
 CONSTRAINT [PK_comentarios] PRIMARY KEY CLUSTERED 
(
	[id_comentario] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[comercios]    Script Date: 4/11/2020 16:33:40 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[comercios](
	[id_comercio] [int] IDENTITY(1,1) NOT NULL,
	[id_rubro] [int] NOT NULL,
	[nombre] [varchar](100) NOT NULL,
	[descripcion] [varchar](200) NOT NULL,
 CONSTRAINT [PK_comercios] PRIMARY KEY CLUSTERED 
(
	[id_comercio] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ofertas]    Script Date: 4/11/2020 16:33:40 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ofertas](
	[id_oferta] [int] IDENTITY(1,1) NOT NULL,
	[titulo] [varchar](50) NOT NULL,
	[descripcion] [varchar](50) NOT NULL,
	[precio] [float] NOT NULL,
	[id_comercio] [int] NOT NULL,
 CONSTRAINT [PK_ofertas] PRIMARY KEY CLUSTERED 
(
	[id_oferta] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[respuestas]    Script Date: 4/11/2020 16:33:40 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[respuestas](
	[id_respuesta] [int] IDENTITY(1,1) NOT NULL,
	[comentario] [varchar](150) NOT NULL,
 CONSTRAINT [PK_respuestas] PRIMARY KEY CLUSTERED 
(
	[id_respuesta] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[rubros]    Script Date: 4/11/2020 16:33:40 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[rubros](
	[id_rubro] [int] IDENTITY(1,1) NOT NULL,
	[nombre] [varchar](100) NOT NULL,
	[descripcion] [varchar](200) NOT NULL,
 CONSTRAINT [PK_rubros] PRIMARY KEY CLUSTERED 
(
	[id_rubro] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[usuarios]    Script Date: 4/11/2020 16:33:40 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[usuarios](
	[id_usuario] [int] IDENTITY(1,1) NOT NULL,
	[nombre] [varchar](100) NOT NULL,
	[usuario] [varchar](100) NOT NULL,
	[contrasena] [varchar](100) NOT NULL,
 CONSTRAINT [PK_usuarios] PRIMARY KEY CLUSTERED 
(
	[id_usuario] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[comentarios] ON 

INSERT [dbo].[comentarios] ([id_comentario], [id_usuario], [comentario], [id_comercio], [puntaje], [id_respuesta]) VALUES (1, 1, N'Buen comercio', 1, 5, 1)
INSERT [dbo].[comentarios] ([id_comentario], [id_usuario], [comentario], [id_comercio], [puntaje], [id_respuesta]) VALUES (2, 1, N'Traeme 5 langostinos apanados a la casa NÂ°4!', 3, 5, 2)
INSERT [dbo].[comentarios] ([id_comentario], [id_usuario], [comentario], [id_comercio], [puntaje], [id_respuesta]) VALUES (3, 1, N'Yo quiero las piesas de sushi', 3, 5, NULL)
INSERT [dbo].[comentarios] ([id_comentario], [id_usuario], [comentario], [id_comercio], [puntaje], [id_respuesta]) VALUES (6, 1, N'Un comentario', 4, 3, NULL)
INSERT [dbo].[comentarios] ([id_comentario], [id_usuario], [comentario], [id_comercio], [puntaje], [id_respuesta]) VALUES (7, 1, N'Otro comentario', 1, 2, NULL)
INSERT [dbo].[comentarios] ([id_comentario], [id_usuario], [comentario], [id_comercio], [puntaje], [id_respuesta]) VALUES (8, 1, N'Comercio con mÃ¡s comentarios', 1, 5, NULL)
SET IDENTITY_INSERT [dbo].[comentarios] OFF
GO
SET IDENTITY_INSERT [dbo].[comercios] ON 

INSERT [dbo].[comercios] ([id_comercio], [id_rubro], [nombre], [descripcion]) VALUES (1, 1, N'Nuevo comercio de turismo', N'una descipción')
INSERT [dbo].[comercios] ([id_comercio], [id_rubro], [nombre], [descripcion]) VALUES (2, 2, N'Restaurante de Juan Pedro', N'El mejor restaurante de la zona')
INSERT [dbo].[comercios] ([id_comercio], [id_rubro], [nombre], [descripcion]) VALUES (3, 2, N'Restaurante de Martin', N'El mejor Sushi para comer')
INSERT [dbo].[comercios] ([id_comercio], [id_rubro], [nombre], [descripcion]) VALUES (4, 2, N'Restaurante de UTN', N'Los mejores postres de la zona')
INSERT [dbo].[comercios] ([id_comercio], [id_rubro], [nombre], [descripcion]) VALUES (5, 3, N'Farmacia de Mario', N'LA mejor farmacia de la zona')
INSERT [dbo].[comercios] ([id_comercio], [id_rubro], [nombre], [descripcion]) VALUES (6, 3, N'Peluqueria del barrio', N'La unica y mejor peluqueria del barrio')
SET IDENTITY_INSERT [dbo].[comercios] OFF
GO
SET IDENTITY_INSERT [dbo].[ofertas] ON 

INSERT [dbo].[ofertas] ([id_oferta], [titulo], [descripcion], [precio], [id_comercio]) VALUES (1, N'Nueva oferta para comercio en el rubro Turismo', N'Una descripciooooooooooooon', 122, 1)
INSERT [dbo].[ofertas] ([id_oferta], [titulo], [descripcion], [precio], [id_comercio]) VALUES (2, N'Langostino apanado', N'Ricaso', 100, 3)
INSERT [dbo].[ofertas] ([id_oferta], [titulo], [descripcion], [precio], [id_comercio]) VALUES (3, N'Sushi 300 piezas', N'300 piezas de sushi', 40000, 3)
INSERT [dbo].[ofertas] ([id_oferta], [titulo], [descripcion], [precio], [id_comercio]) VALUES (4, N'Helado', N'3kg de helado casero', 500, 4)
INSERT [dbo].[ofertas] ([id_oferta], [titulo], [descripcion], [precio], [id_comercio]) VALUES (5, N'Ibupirac 300g', N'Para dolores de cabeza y cuerpo', 250, 5)
INSERT [dbo].[ofertas] ([id_oferta], [titulo], [descripcion], [precio], [id_comercio]) VALUES (6, N'Corte para Hombres', N'en 30 minutos ya estas listo', 500, 6)
INSERT [dbo].[ofertas] ([id_oferta], [titulo], [descripcion], [precio], [id_comercio]) VALUES (7, N'Corte para Mujeres', N'en 30 minutos esta lista!', 500, 6)
SET IDENTITY_INSERT [dbo].[ofertas] OFF
GO
SET IDENTITY_INSERT [dbo].[respuestas] ON 

INSERT [dbo].[respuestas] ([id_respuesta], [comentario]) VALUES (1, N'Una respuesta')
INSERT [dbo].[respuestas] ([id_respuesta], [comentario]) VALUES (2, N'Nos quedamos SIN!')
INSERT [dbo].[respuestas] ([id_respuesta], [comentario]) VALUES (3, N'Gracias!!')
INSERT [dbo].[respuestas] ([id_respuesta], [comentario]) VALUES (4, N'Ami me parecio muy buen lugar')
SET IDENTITY_INSERT [dbo].[respuestas] OFF
GO
SET IDENTITY_INSERT [dbo].[rubros] ON 

INSERT [dbo].[rubros] ([id_rubro], [nombre], [descripcion]) VALUES (1, N'Turismo', N'asdsad')
INSERT [dbo].[rubros] ([id_rubro], [nombre], [descripcion]) VALUES (2, N'Gastronomia', N'Comercios para buscar algo de comer o comer en el lugar')
INSERT [dbo].[rubros] ([id_rubro], [nombre], [descripcion]) VALUES (3, N'Cuidado Personal', N'Remedios, cosmeticos, todo lo que necesites para tu cuerpo
')
SET IDENTITY_INSERT [dbo].[rubros] OFF
GO
SET IDENTITY_INSERT [dbo].[usuarios] ON 

INSERT [dbo].[usuarios] ([id_usuario], [nombre], [usuario], [contrasena]) VALUES (1, N'Juan Pedro', N'pedritopv', N'$2a$10$owkd/0x83AjRHZxSnKyI3eQRgO95i2LpAh1jev9SILIAqtuMQqTq6')
SET IDENTITY_INSERT [dbo].[usuarios] OFF
GO
ALTER TABLE [dbo].[comentarios]  WITH CHECK ADD  CONSTRAINT [FK_comentarios_comercios] FOREIGN KEY([id_comercio])
REFERENCES [dbo].[comercios] ([id_comercio])
GO
ALTER TABLE [dbo].[comentarios] CHECK CONSTRAINT [FK_comentarios_comercios]
GO
ALTER TABLE [dbo].[comentarios]  WITH CHECK ADD  CONSTRAINT [FK_comentarios_respuestas] FOREIGN KEY([id_respuesta])
REFERENCES [dbo].[respuestas] ([id_respuesta])
GO
ALTER TABLE [dbo].[comentarios] CHECK CONSTRAINT [FK_comentarios_respuestas]
GO
ALTER TABLE [dbo].[comentarios]  WITH CHECK ADD  CONSTRAINT [FK_comentarios_usuarios] FOREIGN KEY([id_usuario])
REFERENCES [dbo].[usuarios] ([id_usuario])
GO
ALTER TABLE [dbo].[comentarios] CHECK CONSTRAINT [FK_comentarios_usuarios]
GO
ALTER TABLE [dbo].[comercios]  WITH CHECK ADD  CONSTRAINT [FK_comercios_rubros] FOREIGN KEY([id_rubro])
REFERENCES [dbo].[rubros] ([id_rubro])
GO
ALTER TABLE [dbo].[comercios] CHECK CONSTRAINT [FK_comercios_rubros]
GO
ALTER TABLE [dbo].[ofertas]  WITH CHECK ADD  CONSTRAINT [FK_ofertas_comercios] FOREIGN KEY([id_comercio])
REFERENCES [dbo].[comercios] ([id_comercio])
GO
ALTER TABLE [dbo].[ofertas] CHECK CONSTRAINT [FK_ofertas_comercios]
GO
